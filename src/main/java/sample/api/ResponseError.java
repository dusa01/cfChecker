
package sample.api;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * AWS通信時に、API Gateway情報へ何も設定していない場合に返却される
 * エラーレスポンス情報の構造を提供します。
 *
 * @author chiku-makoto / takabayahsi-ko
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class ResponseError {
    /** エラーメッセージ */
    private String errorMessage;
    /** エラーのタイプ */
    private String errorType;
    /** スタックトレース情報 */
    private ArrayList<String> stackTrace;

    /**
     * エラーメッセージを取得します。
     *
     * @return エラーメッセージ
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * エラーメッセージを設定します。
     *
     * @param errorMessage エラーメッセージ
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * エラータイプを取得します。
     *
     * @return エラータイプ
     */
    public String getErrorType() {
        return errorType;
    }

    /**
     * エラータイプを設定します。
     *
     * @param errorType エラータイプ
     */
    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    /**
     * エラーのスタックトレース情報を取得します
     *
     * @return スタックトレース情報
     */
    public ArrayList<String> getStackTrace() {
        return stackTrace;
    }

    /**
     * エラーのスタックトレース情報を設定します。
     *
     * @param stackTrace スタックトレース情報
     */
    public void setStackTrace(ArrayList<String> stackTrace) {
        this.stackTrace = stackTrace;
    }
}
