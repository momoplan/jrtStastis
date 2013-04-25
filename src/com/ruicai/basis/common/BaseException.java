package com.ruicai.basis.common;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BaseException extends Exception {

	private static final long serialVersionUID = 5404766056023341965L;

	// 异常级联
	protected Throwable rootCause = null;

	// 异常集合（多元化）
	private List exceptions = new ArrayList();

	// 消息key
	private String messageKey = null;

	// 复合式消息
	private Object[] messageArgs = null;

	public BaseException() {
		super();
	}

	public BaseException(Throwable rootCause) {
		this.rootCause = rootCause;
	}

	public List getExceptions() {
		return exceptions;
	}

	public void setExceptions(BaseException baseException) {
		exceptions.add(baseException);
	}

	public Object[] getMessageArgs() {
		return messageArgs;
	}

	public void setMessageArgs(Object[] messageArgs) {
		this.messageArgs = messageArgs;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public Throwable getRootCause() {
		return rootCause;
	}

	public void setRootCause(Throwable rootCause) {
		this.rootCause = rootCause;
	}

	public void printStackTrace() {
		printStackTrace(System.err);
	}

	public void printStackTrace(PrintStream out) {
		printStackTrace(new PrintWriter(out));
	}

	public void printStackTrace(PrintWriter writer) {
		super.printStackTrace(writer);

		if (getRootCause() != null) {
			getRootCause().printStackTrace(writer);
		}

		writer.flush();
	}

}
