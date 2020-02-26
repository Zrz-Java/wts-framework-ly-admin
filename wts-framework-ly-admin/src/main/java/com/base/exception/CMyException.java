package com.base.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class CMyException extends Exception
{
  private static final long serialVersionUID = 8407347400205826118L;
  protected int errNo;
  protected Throwable rootCause;

  public CMyException(int _errNo)
  {
    this.errNo = 0;
    this.rootCause = null;
    this.errNo = _errNo;
  }

  public CMyException(int _errNo, String _sMsg) {
    super(_sMsg);
    this.errNo = 0;
    this.rootCause = null;
    this.errNo = _errNo;
  }

  public CMyException(String _sMsg) {
    super(_sMsg);
    this.errNo = 0;
    this.rootCause = null;
  }

  public CMyException(int _errNo, String _sMsg, Throwable _rootCause) {
    super(_sMsg);
    this.errNo = 0;
    this.rootCause = null;
    this.errNo = _errNo;
    this.rootCause = _rootCause;
  }

  public CMyException(String _sMsg, Throwable _rootCause) {
    super(_sMsg);
    this.errNo = 0;
    this.rootCause = null;
    this.rootCause = _rootCause;
  }

  public int getErrNo() {
    return this.errNo;
  }

  public Throwable getRootCause() {
    return this.rootCause;
  }

  public String getErrNoMsg() {
    return "";
  }

  public String getMyMessage() {
    return super.getMessage();
  }

  public String toString() {
    String sMessage = "[ERR-" + this.errNo + "] " + getMyMessage();
    return sMessage;
  }

  public String getMessage() {
    String sMessage = toString();
    if (this.rootCause != null)
      sMessage = sMessage + "\r\n<-- " + this.rootCause.toString();
    return sMessage;
  }

  public String getLocalizedMessage() {
    return getMessage();
  }

  public void printStackTrace(PrintStream _ps) {
    if (this.rootCause == null) {
      super.printStackTrace(_ps);
    } else {
      Throwable root = this.rootCause;
      synchronized (_ps) {
        _ps.println(toString());
        Throwable temp = null;
        while (root instanceof CMyException) {
          _ps.println("<-- " + root.toString());
          temp = root;
          root = ((CMyException)root).getRootCause();
          if (root == null) {
            temp.printStackTrace(_ps);
            break;
          }
        }
        if (root != null) {
          _ps.print("<-- ");
          root.printStackTrace(_ps);
        }
      }
    }
  }

  public void printStackTrace(PrintWriter _pw) {
    if (this.rootCause == null) {
      super.printStackTrace(_pw);
    } else {
      Throwable root = this.rootCause;
      synchronized (_pw) {
        _pw.println(toString());
        Throwable preRoot = null;
        for (; root instanceof CMyException; _pw.println(preRoot.toString())) {
          _pw.print("<-- ");
          preRoot = root;
          root = ((CMyException)root).getRootCause();
          if (root != null)
            continue;
          preRoot.printStackTrace(_pw);
          break;
        }

        if (root != null) {
          _pw.print("<-- ");
          root.printStackTrace(_pw);
        }
      }
    }
  }

  public static final void main(String[] args) {
    CMyException fire0 = new CMyException(1, "my exception 0");
    CMyException fire = new CMyException(1, "my exception 1", fire0);
    CMyException fire2 = new CMyException(10, "my exception 2", fire);
    fire2.printStackTrace(System.out);
    System.out.println("-------------------");
    System.out.println(fire2.getMessage());
    System.out.println("-------------------");
    try {
      int a = 0;
      int b = 1 / a;
      System.out.println(b);
    }
    catch (Exception localException)
    {
    }
  }
}