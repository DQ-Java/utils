package com.pers.util;

import com.pers.bean.Trail;
import com.pers.constant.Constants;

import java.math.BigDecimal;
import java.util.*;


/**
 * 工具类
 *
 * @author littlePigHome
 */
public class TrailUtil {

    /**
     * 贷款方式试算
     *
     * @param amortMethod 还款方法 1-等额本息 2-等额本金 3-分期付款 4-利随本清 5-随借随还 6-按月付息，到期还本
     *                    7-按月付费，到期还本
     * @param startDate   借款起始日期
     * @param loanAmount  贷款金额
     * @param rate        贷款利率-年利率 月利率=年利率/12 日利率=年利率/360
     * @param feeRate     手续费率
     * @param tenor       贷款期数
     * @param tenorType   贷款期数类型，1-月，2-天
     * @return
     */
    public static List<Trail> trialInterface(String amortMethod, Date startDate, BigDecimal loanAmount,
                                                   BigDecimal rate, BigDecimal feeRate, int tenor, int tenorType) {
        List<Trail> tbList = new ArrayList<Trail>();
        // 等额本息处理
        if (amortMethod.equalsIgnoreCase(Constants.AMORT_EQUAL_PI)) {
            tbList = computeEPI(startDate, loanAmount, rate, feeRate, tenor, tenorType);
        }
        return tbList;

    }

    /**
     * 计算等额本息
     *
     * @param startDate
     * @param loanAmount
     * @param rate
     * @param feeRate
     * @param tenor
     * @param tenorType
     */
    public static List<Trail> computeEPI(Date startDate, BigDecimal loanAmount, BigDecimal rate, BigDecimal feeRate,
                                             int tenor, int tenorType) {
        List<Trail> tbList = new ArrayList<Trail>();
        BigDecimal monthlyInterest = BigDecimal.ZERO;
        BigDecimal monthlyPrincipal = BigDecimal.ZERO;
        BigDecimal monthlyFee = BigDecimal.ZERO;
        BigDecimal monthlyInsuranceFee = BigDecimal.ZERO;
        BigDecimal monthlyPayment = BigDecimal.ZERO;
        BigDecimal monthlyRate = BigDecimal.ZERO;
        BigDecimal amount = loanAmount;
        monthlyRate = rate.divide(BigDecimal.valueOf(12), 8, BigDecimal.ROUND_HALF_UP).setScale(8,
                BigDecimal.ROUND_HALF_UP);
        // 计算每月所还本金+利息，进行四舍五入处理
        monthlyPayment = loanAmount.multiply(monthlyRate)
                .multiply(BigDecimal.valueOf(Math.pow((1 + monthlyRate.doubleValue()), tenor)).divide(
                        BigDecimal.valueOf(Math.pow((1 + monthlyRate.doubleValue()), tenor) - 1), 8,
                        BigDecimal.ROUND_HALF_UP))
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        for (int i = 1; i <= tenor; i++) {
            Trail tb = new Trail();
            // 剩余未还本金金额=贷款总金额-已还本金
            // 利息等于剩余本金*月利率
            monthlyInterest = loanAmount.multiply(monthlyRate).setScale(2, BigDecimal.ROUND_HALF_UP);
            monthlyPrincipal = monthlyPayment.subtract(monthlyInterest);
            if (i == tenor) {
                monthlyPrincipal = loanAmount;
                monthlyInterest = monthlyPayment.subtract(monthlyPrincipal);
            } else {
                loanAmount = loanAmount.subtract(monthlyPrincipal);
            }
            tb.setPrincipal(monthlyPrincipal);
            tb.setInterest(monthlyInterest);
            tb.setTenor(i);
            tb.setPaymentAmount(monthlyPrincipal.add(monthlyInterest).add(monthlyFee).add(monthlyInsuranceFee));
            tbList.add(tb);
        }
        return tbList;
    }

}