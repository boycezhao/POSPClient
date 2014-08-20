package com.cssweb.payment.pospclient;

/**
 * Created by chenhf on 2014/7/21.
 */
public class MsgHeader {

    // 总共46字节

    public static final int MSG_HEADER_LEN = 46;

    private FieldMsgHeaderLen len;
    private FieldMsgHeaderVer ver;
    private FieldMsgHeaderTotalLen totalLen;
    private FieldMsgHeaderDest dest;
    private FieldMsgHeaderSource src;
    private FieldMsgHeaderReserved reserved;
    private FieldMsgHeaderBatchNo batchNo;
    private FieldMsgHeaderTradeInfo tradeInfo;
    private FieldMsgHeaderUserInfo uerInfo;
    private FieldMsgHeaderRejectCode rejectCode;


    public FieldMsgHeaderLen getLen() {
        return len;
    }

    public void setLen(FieldMsgHeaderLen len) {
        this.len = len;
    }

    public FieldMsgHeaderVer getVer() {
        return ver;
    }

    public void setVer(FieldMsgHeaderVer ver) {
        this.ver = ver;
    }

    public FieldMsgHeaderTotalLen getTotalLen() {
        return totalLen;
    }

    public void setTotalLen(FieldMsgHeaderTotalLen totalLen) {
        this.totalLen = totalLen;
    }

    public FieldMsgHeaderDest getDest() {
        return dest;
    }

    public void setDest(FieldMsgHeaderDest dest) {
        this.dest = dest;
    }

    public FieldMsgHeaderSource getSrc() {
        return src;
    }

    public void setSrc(FieldMsgHeaderSource src) {
        this.src = src;
    }

    public FieldMsgHeaderReserved getReserved() {
        return reserved;
    }

    public void setReserved(FieldMsgHeaderReserved reserved) {
        this.reserved = reserved;
    }

    public FieldMsgHeaderBatchNo getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(FieldMsgHeaderBatchNo batchNo) {
        this.batchNo = batchNo;
    }

    public FieldMsgHeaderTradeInfo getTradeInfo() {
        return tradeInfo;
    }

    public void setTradeInfo(FieldMsgHeaderTradeInfo tradeInfo) {
        this.tradeInfo = tradeInfo;
    }

    public FieldMsgHeaderUserInfo getUerInfo() {
        return uerInfo;
    }

    public void setUerInfo(FieldMsgHeaderUserInfo uerInfo) {
        this.uerInfo = uerInfo;
    }

    public FieldMsgHeaderRejectCode getRejectCode() {
        return rejectCode;
    }

    public void setRejectCode(FieldMsgHeaderRejectCode rejectCode) {
        this.rejectCode = rejectCode;
    }

    public boolean decodeMsgHeader(byte[] msgHeader)
    {
        return true;
    }

    public boolean encodeMsgHeader()
    {
        return true;
    }

}
