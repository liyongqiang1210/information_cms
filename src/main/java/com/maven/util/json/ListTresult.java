package com.maven.util.json;

import java.util.ArrayList;
import java.util.List;

public class ListTresult
{

    private boolean success;

    private String message;

    // Model对象
    private List<ArrayList<String>> model;

    /**
     * 使用静态方法进行构造
     * 
     * @author
     * @date 2013年10月10日 下午3:40:58
     */
    public ListTresult()
    {

    }

    /**
     * 构建成功的交互对象, 数据为单个结果对象
     * 
     * @return JSON封装对象
     */
    public static <E> ListTresult buildSuccessResult(List<ArrayList<String>> data)
    {

        ListTresult result = new ListTresult();

        if (data != null)
        {
            result.setSuccess(true);
            result.setMessage("OK");
            result.setModel(data);
        }
        else
        {
            result.setSuccess(false);
            result.setMessage("数据为空");
        }
        return result;
    }

    public static <E> ListTresult buildSuccessResult(String message, boolean state)
    {

        ListTresult result = new ListTresult();
        result.setSuccess(state);
        result.setMessage(message);
        return result;
    }
    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public List<ArrayList<String>> getModel()
    {
        return model;
    }

    public void setModel(List<ArrayList<String>> model)
    {
        this.model = model;
    }


}
