package com.kedi.common.util.sql;

import com.kedi.common.web.PageBean;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 生成slq语句工具类
 *
 * @author xy
 */
public class SQL {
    private final List<String> select = new ArrayList<>();
    private final List<String> from = new ArrayList<>();
    private final List<JoinSql> join = new ArrayList<>();
    private final List<String> where = new ArrayList<>();
    private final List<String> groupBy = new ArrayList<>();
    private final List<String> having = new ArrayList<>();
    private final List<String> orderBy = new ArrayList<>();
    private final List<String[]> values = new ArrayList<>();
    private final List<String[]> set = new ArrayList<>();
    private final List<String[]> duplicateUpdate = new ArrayList<>();
    private String insert;
    private String update;
    private String delete;
    private int[] limit;
    private final String ITERATOR_SEPARATOR = ";\n";

    private String createSql(ActionType actionType) {
        StringBuilder sql = new StringBuilder();
        switch (actionType) {
            case SELECT: {
                if (select.size() > 0) {
                    sql.append("SELECT ").append(StringUtils.join(select, ","));
                }
                break;
            }
            case FROM: {
                if (from.size() > 0) {
                    sql.append("FROM ").append(StringUtils.join(from, ","));
                }
                break;
            }
            case JOIN: {
                if (join.size() > 0) {
                    List<String> joinSqls = new ArrayList<>(join.size());
                    for (JoinSql joinSql : join) {
                        // 左连接
                        if (joinSql.getActionType() == ActionType.LEFT_JOIN) {
                            joinSqls.add("LEFT JOIN " + joinSql.getSql());
                        }
                        // 全连接
                        else if (joinSql.getActionType() == ActionType.INNER_JOIN) {
                            joinSqls.add("INNER JOIN " + joinSql.getSql());
                        }
                    }
                    sql.append(StringUtils.join(joinSqls, " "));
                }
                break;
            }
            case WHERE: {
                if (where.size() > 0) {
                    sql.append("WHERE ").append(StringUtils.join(where, " AND "));
                }
                break;
            }
            case GROUP_BY: {
                if (groupBy.size() > 0) {
                    sql.append("GROUP BY ").append(StringUtils.join(groupBy, ","));
                }
                break;
            }
            case HAVING: {
                if (having.size() > 0) {
                    sql.append("HAVING ").append(StringUtils.join(having, " AND "));
                }
                break;
            }
            case ORDER_BY: {
                if (orderBy.size() > 0) {
                    sql.append("ORDER BY ").append(StringUtils.join(orderBy, ","));
                }
                break;
            }
            case LIMIT: {
                if (limit != null && limit.length == 2) {
                    sql.append(String.format("LIMIT %d,%d", limit[0], limit[1]));
                }
                break;
            }
            case INSERT: {
                if (StringUtils.isNotBlank(insert)) {
                    sql.append("INSERT INTO ").append(insert);
                }
                break;
            }
            case VALUES: {
                if (values.size() > 0) {
                    String[] columnArr = new String[values.size()];
                    String[] valueArr = new String[values.size()];
                    for (int i = 0; i < values.size(); i++) {
                        String[] value = values.get(i);
                        columnArr[i] = value[0];
                        valueArr[i] = value[1];
                    }
                    sql.append(String.format("(%s) VALUES (%s)", StringUtils.join(columnArr, ","),
                            StringUtils.join(valueArr, ",")));
                }
                break;
            }
            case DUPLICATE_UPDATE: {
                if (duplicateUpdate.size() > 0) {
                    String[] duplicateUpdateArr = new String[duplicateUpdate.size()];
                    for (int i = 0; i < duplicateUpdateArr.length; i++) {
                        String[] duplicateUpdateTmp = duplicateUpdate.get(i);
                        duplicateUpdateArr[i] = duplicateUpdateTmp[0] + "=" + duplicateUpdateTmp[1];
                    }
                    sql.append("ON DUPLICATE KEY UPDATE ").append(StringUtils.join(duplicateUpdateArr, ","));
                }
                break;
            }
            case UPDATE: {
                if (StringUtils.isNotBlank(update)) {
                    sql.append("UPDATE ").append(update);
                }
                break;
            }
            case SET: {
                if (set.size() > 0) {
                    String[] setArr = new String[set.size()];
                    for (int i = 0; i < setArr.length; i++) {
                        String[] setTmp = set.get(i);
                        setArr[i] = setTmp[0] + "=" + setTmp[1];
                    }
                    sql.append("SET ").append(StringUtils.join(setArr, ","));
                }
                break;
            }
            case DELETE: {
                if (StringUtils.isNotBlank(delete)) {
                    sql.append("DELETE FROM ").append(delete);
                }
                break;
            }
            default:
                break;
        }
        return sql.toString();
    }

    public SQL select(String select) {
        if (StringUtils.isNotBlank(select)) {
            this.select.add(select);
        }
        return this;
    }

    public SQL select(boolean condition, String select) {
        if (condition) {
            select(select);
        }
        return this;
    }

    public SQL select(SqlAssert selectAssert) {
        boolean condition = selectAssert.check();
        String select = selectAssert.create();
        return select(condition, select);
    }

    public SQL from(String table) {
        if (StringUtils.isNotBlank(table)) {
            this.from.add(table);
        }
        return this;
    }

    public SQL from(boolean condition, String table) {
        if (condition) {
            from(table);
        }
        return this;
    }

    public SQL from(SqlAssert fromAssert) {
        boolean condition = fromAssert.check();
        String table = fromAssert.create();
        return from(condition, table);
    }

    public SQL from(SQL subSql, String alias) {
        return from("(" + subSql.build() + ") " + alias);
    }

    public SQL leftJoin(String leftJoin) {
        if (StringUtils.isNotBlank(leftJoin)) {
            this.join.add(new JoinSql(ActionType.LEFT_JOIN, leftJoin));
        }
        return this;
    }

    public SQL leftJoin(String leftJoin, String... ands) {
        if (StringUtils.isNotBlank(leftJoin)) {
            StringBuilder sb = new StringBuilder(leftJoin);
            if (ands != null && ands.length > 0) {
                for (String and : ands) {
                    if (StringUtils.isNotBlank(and)) {
                        sb.append(" AND ").append(and);
                    }
                }
            }
            this.join.add(new JoinSql(ActionType.LEFT_JOIN, sb.toString()));
        }
        return this;
    }

    public SQL leftJoin(boolean condition, String leftJoin) {
        if (condition) {
            leftJoin(leftJoin);
        }
        return this;
    }

    public SQL leftJoin(SqlAssert leftJoinAssert) {
        boolean condition = leftJoinAssert.check();
        String leftJoin = leftJoinAssert.create();
        return leftJoin(condition, leftJoin);
    }

    public SQL leftJoin(SQL subSql, String alias, String on) {
        return leftJoin("(" + subSql.build() + ") " + alias + " ON " + on);
    }

    public SQL innerJoin(String innerJoin) {
        if (StringUtils.isNotBlank(innerJoin)) {
            this.join.add(new JoinSql(ActionType.INNER_JOIN, innerJoin));
        }
        return this;
    }

    public SQL innerJoin(String innerJoin, String... ands) {
        if (StringUtils.isNotBlank(innerJoin)) {
            StringBuilder sb = new StringBuilder(innerJoin);
            if (ands != null && ands.length > 0) {
                for (String and : ands) {
                    if (StringUtils.isNotBlank(and)) {
                        sb.append(" AND ").append(and);
                    }
                }
            }
            this.join.add(new JoinSql(ActionType.INNER_JOIN, sb.toString()));
        }
        return this;
    }

    public SQL innerJoin(boolean condition, String innerJoin) {
        if (condition) {
            innerJoin(innerJoin);
        }
        return this;
    }

    public SQL innerJoin(SqlAssert innerJoinAssert) {
        boolean condition = innerJoinAssert.check();
        String innerJoin = innerJoinAssert.create();
        return innerJoin(condition, innerJoin);
    }

    public SQL innerJoin(SQL subSql, String alias, String on) {
        return innerJoin("(" + subSql.build() + ") " + alias + " ON " + on);
    }

    public SQL where(String where) {
        if (StringUtils.isNotBlank(where)) {
            this.where.add(where);
        }
        return this;
    }

    public SQL and(String where) {
        if (StringUtils.isNotBlank(where)) {
            where(where);
        }
        return this;
    }

    public SQL where(boolean condition, String where) {
        if (condition) {
            where(where);
        }
        return this;
    }

    public SQL and(boolean condition, String where) {
        if (condition) {
            where(where);
        }
        return this;
    }

    public SQL where(SqlAssert whereAssert) {
        boolean condition = whereAssert.check();
        String where = whereAssert.create();
        return where(condition, where);
    }

    public SQL and(SqlAssert whereAssert) {
        boolean condition = whereAssert.check();
        String where = whereAssert.create();
        return where(condition, where);
    }

    public SQL groupBy(String groupBy) {
        if (StringUtils.isNotBlank(groupBy)) {
            this.groupBy.add(groupBy);
        }
        return this;
    }

    public SQL groupBy(boolean condition, String groupBy) {
        if (condition) {
            groupBy(groupBy);
        }
        return this;
    }

    public SQL groupBy(SqlAssert groupByAssert) {
        boolean condition = groupByAssert.check();
        String groupBy = groupByAssert.create();
        return groupBy(condition, groupBy);
    }

    public SQL having(String having) {
        if (StringUtils.isNotBlank(having)) {
            this.having.add(having);
        }
        return this;
    }

    public SQL having(boolean condition, String having) {
        if (condition) {
            having(having);
        }
        return this;
    }

    public SQL having(SqlAssert havingAssert) {
        boolean condition = havingAssert.check();
        String having = havingAssert.create();
        return having(condition, having);
    }

    public SQL orderBy(String orderBy) {
        if (StringUtils.isNotBlank(orderBy)) {
            this.orderBy.add(orderBy);
        }
        return this;
    }

    public SQL orderBy(boolean condition, String orderBy) {
        if (condition) {
            orderBy(orderBy);
        }
        return this;
    }

    public SQL orderBy(SqlAssert orderByAssert) {
        boolean condition = orderByAssert.check();
        String orderBy = orderByAssert.create();
        return orderBy(condition, orderBy);
    }

    public SQL limit(int position, int offSet) {
        if (position < 0 || offSet < 0) {
            throw new IllegalArgumentException("LIMIT起始位置和偏移量不能为空");
        }
        this.limit = new int[]{position, offSet};
        return this;
    }

    public SQL limit(boolean condition, int position, int offSet) {
        if (condition) {
            limit(position, offSet);
        }
        return this;
    }

    public SQL limit(SqlAssert limitAssert) {
        boolean condition = limitAssert.check();
        String[] limit = limitAssert.createArr();
        try {
            int position = Integer.parseInt(limit[0]);
            int offSet = Integer.parseInt(limit[1]);
            limit(condition, position, offSet);
        } catch (Exception ex) {
            throw new IllegalArgumentException("分页数据错误", ex);
        }
        return this;
    }

    public SQL limit(PageBean page) {
        if (page == null) {
            throw new IllegalArgumentException("分页对象不能为空");
        }
        this.limit = page.paginate();
        return this;
    }

    public SQL limit(boolean condition, PageBean page) {
        if (condition) {
            limit(page);
        }
        return this;
    }

    public SQL insert(String table) {
        if (StringUtils.isNotBlank(table)) {
            this.insert = table;
        }
        return this;
    }

    public SQL insert(boolean condition, String insert) {
        if (condition) {
            insert(insert);
        }
        return this;
    }

    public SQL insert(SqlAssert insertAssert) {
        boolean condition = insertAssert.check();
        String insert = insertAssert.create();
        return insert(condition, insert);
    }

    public SQL values(String column, String value) {
        if (StringUtils.isNotBlank(column) && StringUtils.isNotBlank(value)) {
            this.values.add(new String[]{column, value});
        }
        return this;
    }

    public SQL values(boolean condition, String column, String value) {
        if (condition) {
            values(column, value);
        }
        return this;
    }

    public SQL values(SqlAssert valuesAssert) {
        boolean condition = valuesAssert.check();
        String[] values = valuesAssert.createArr();
        try {
            String column = values[0];
            String value = values[1];
            values(condition, column, value);
        } catch (Exception ex) {
            throw new IllegalArgumentException("insert数据错误", ex);
        }
        return this;
    }

    public SQL duplicateUpdate(String column, String value) {
        if (StringUtils.isNotBlank(column) && StringUtils.isNotBlank(value)) {
            this.duplicateUpdate.add(new String[]{column, value});
        }
        return this;
    }

    public SQL duplicateUpdate(boolean condition, String column, String value) {
        if (condition) {
            duplicateUpdate(column, value);
        }
        return this;
    }

    public SQL update(String table) {
        if (StringUtils.isNotBlank(table)) {
            this.update = table;
        }
        return this;
    }

    public SQL update(boolean condition, String table) {
        if (condition) {
            update(table);
        }
        return this;
    }

    public SQL update(SqlAssert updateAssert) {
        boolean condition = updateAssert.check();
        String table = updateAssert.create();
        return update(condition, table);
    }

    public SQL set(String column, String value) {
        if (StringUtils.isNotBlank(column) && StringUtils.isNotBlank(value)) {
            this.set.add(new String[]{column, value});
        }
        return this;
    }

    public SQL set(boolean condition, String column, String value) {
        if (condition) {
            set(column, value);
        }
        return this;
    }

    public SQL set(SqlAssert setAssert) {
        boolean condition = setAssert.check();
        String[] set = setAssert.createArr();
        try {
            String column = set[0];
            String value = set[1];
            set(condition, column, value);
        } catch (Exception ex) {
            throw new IllegalArgumentException("set数据错误", ex);
        }
        return this;
    }

    public SQL delete(String table) {
        if (StringUtils.isNotBlank(table)) {
            this.delete = table;
        }
        return this;
    }

    public SQL delete(boolean condition, String table) {
        if (condition) {
            delete(table);
        }
        return this;
    }

    public SQL delete(SqlAssert deleteAssert) {
        boolean condition = deleteAssert.check();
        String table = deleteAssert.create();
        return delete(condition, table);
    }

    /**
     * sql处理方法,把当前sql对象调用权限转移给调用者，可以直接操作当前sql对象
     *
     * @param sqlProcessor sql处理类
     * @return 当前sql对象
     */
    public SQL sqlProcess(SqlProcessor sqlProcessor) {
        sqlProcessor.process(this);
        return this;
    }

    /**
     * 生成sql字符串
     */
    public String build() {
        ActionType[] actionTypes = ActionType.values();
        List<String> sqls = new ArrayList<>(ActionType.values().length);
        for (ActionType actionType : actionTypes) {
            sqls.add(createSql(actionType));
        }
        sqls.removeIf(StringUtils::isBlank);
        return StringUtils.join(sqls, " ");
    }

    /**
     * 关键字枚举
     */
    enum ActionType {
        SELECT, INSERT, VALUES, DUPLICATE_UPDATE, UPDATE, DELETE, FROM, LEFT_JOIN, INNER_JOIN, JOIN, SET, WHERE, GROUP_BY, HAVING, ORDER_BY, LIMIT
    }

    private class JoinSql {
        private final ActionType actionType;
        private final String sql;

        private JoinSql(ActionType actionType, String sql) {
            this.actionType = actionType;
            this.sql = sql;
        }

        private ActionType getActionType() {
            return actionType;
        }

        private String getSql() {
            return sql;
        }
    }

    public <T> String foreach(List<T> params, SqlIterator<T> sqlIterator) {
        int size = params.size();
        String[] sqls = new String[size];
        for (int i = 0; i < size; i++) {
            T param = params.get(i);
            SQL sql = sqlIterator.iterate(param, i);
            sqls[i] = sql.build();
        }
        return StringUtils.join(sqls, ITERATOR_SEPARATOR);
    }

    public <T> String foreach(T[] params, SqlIterator<T> sqlIterator) {
        int length = params.length;
        String[] sqls = new String[length];
        for (int i = 0; i < length; i++) {
            T param = params[i];
            SQL sql = sqlIterator.iterate(param, i);
            sqls[i] = sql.build();
        }
        return StringUtils.join(sqls, ITERATOR_SEPARATOR);
    }

    public <T> String foreach(List<T> params, ValuesIterator<T> valuesIterator) {
        int size = params.size();
        String name = "";
        String[] values = new String[size];
        for (int i = 0; i < size; i++) {
            T param = params.get(i);
            name = getString(name, values, i, valuesIterator.values(param, i), valuesIterator, param);
        }
        StringBuilder sql = new StringBuilder();
        return sql.append(build()).append("(").append(name).append(")")
                .append(" VALUES ")
                .append(StringUtils.join(values, ",")).toString();
    }

    public <T> String foreach(T[] params, ValuesIterator<T> valuesIterator) {
        int size = params.length;
        String name = "";
        String[] values = new String[size];
        for (int i = 0; i < size; i++) {
            T param = params[i];
            name = getString(name, values, i, valuesIterator.values(param, i), valuesIterator, param);
        }
        StringBuilder sql = new StringBuilder();
        return sql.append(build()).append("(").append(name).append(")")
                .append(" VALUES ")
                .append(StringUtils.join(values, ",")).toString();
    }

    private <T> String getString(String name, String[] values, int i, Map<String, String> values2, ValuesIterator<T> valuesIterator, T param) {
        Map<String, String> valuesMap = values2;
        if (i == 0) {
            StringBuilder nameBuilder = new StringBuilder();
            Set<String> keySet = valuesMap.keySet();
            for (String key : keySet) {
                nameBuilder.append(key).append(",");
            }
            name = nameBuilder.substring(0, nameBuilder.lastIndexOf(","));
        }
        StringBuilder valueBuilder = new StringBuilder();
        Collection<String> valueCollection = valuesMap.values();
        for (String value : valueCollection) {
            valueBuilder.append(value).append(",");
        }
        values[i] = "(" + valueBuilder.substring(0, valueBuilder.lastIndexOf(",")) + ")";
        return name;
    }

    public static void main(String[] args) {
        String sql = new SQL()
                .select("*")
                .from("a")
                .and(false, "a=1")
                .and(true, "b=1")
                .where("1=1")
                .build();
        System.out.printf("----" + sql);
    }
}
