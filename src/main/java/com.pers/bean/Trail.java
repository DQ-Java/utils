package com.pers.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 试算bean
 *
 * @author littlePigHome
 */
public class Trail {

    /**
     * 期数
     */
    private int tenor;
    /**
     * 还款日期
     */
    private Date paymentDate;
    /**
     * 月还本金
     */
    private BigDecimal principal;
    /**
     * 月还利息
     */
    private BigDecimal interest;
    /**
     * 月还款金额
     */
    private BigDecimal paymentAmount;

    /**
     * 获取 期数
     *
     * @return the tenor
     */
    public int getTenor() {
        return tenor;
    }

    /**
     * 设置 期数
     *
     * @param tenor to set
     */
    public void setTenor(int tenor) {
        this.tenor = tenor;
    }

    /**
     * 获取 还款日期
     *
     * @return the paymentDate
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * 设置 还款日期
     *
     * @param paymentDate to set
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * 获取 月还本金
     *
     * @return the principal
     */
    public BigDecimal getPrincipal() {
        return principal;
    }

    /**
     * 设置 月还本金
     *
     * @param principal to set
     */
    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    /**
     * 获取 月还利息
     *
     * @return the interest
     */
    public BigDecimal getInterest() {
        return interest;
    }

    /**
     * 设置 月还利息
     *
     * @param interest to set
     */
    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    /**
     * 获取 月还款金额
     *
     * @return the paymentAmount
     */
    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * 设置 月还款金额
     *
     * @param paymentAmount to set
     */
    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

}
