/**
 * 表头
 */
public enum TableHeadEnum {
    /**
     * 操作票目录
     */
    HEAD1("序号", "计划操作日期", "操作任务"),
    /**
     * 审核预备命令票列表
     */
    HEAD2("序号", "操作任务", "操作日期", "编制日期", "编制人"),
    /**
     *
     */
    HEAD3("票号", "编制人", "审核人", "批准人", "待审核人", "操作任务概述", "编制日期", "操作日期"),
    /**
     *
     */
    HEAD4("序号", "电压", "操作单位", "项号", "操作命令内容", "类型", "状态", "注意事项");

    final private String[] heads;

    TableHeadEnum(String... heads) {
        this.heads = heads;
    }

    public String[] heads() {
        return heads;
    }

}
