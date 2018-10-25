package com.uooguo.newretail.cloud.framework.sms;

/**
 * 短信发送器
 * @author Tiangel
 *
 */
public interface SmsSender {

	/**
	 * 发送短信
	 * @param parameter
	 * @return
	 */
	public SmsSendResult send(SmsParameter parameter);
}
