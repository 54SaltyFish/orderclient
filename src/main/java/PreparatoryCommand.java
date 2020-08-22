import lombok.Data;

import java.util.Date;

/**
 * 预备令
 */
@Data
public class PreparatoryCommand {
    private String id;//指令id
    private Integer ticketNum;//票号
    private String serialNum;//序号
    private String stationName;//站名
    private String dispatcher;//调度员
    private String toSuffer;//受令人
    private Integer itemNum;//项号
    private String createBody;//创建人
    private String orderType;//指令类型   1 正式令    2 预备令
    private String orderStatus;//指令状态  0删除  1待审核(新建)   2审核   3批准
    private Date createTime;//指令创建时间
    private Date updateTime;//指令更新时间
    private String operationSteps;//操作步骤
    private Date orderTime;//下令时间
    private String reserve1;//备用字段1
    private String reserve2;//备用字段2
    private String checker;//审核人
    private String approvedPeople;//批准人
    private Date checkTime;//;审核时间
}
