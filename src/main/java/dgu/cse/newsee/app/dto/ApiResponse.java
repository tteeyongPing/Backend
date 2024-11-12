package dgu.cse.newsee.app.dto;


import java.util.List;

public class ApiResponse {
    private int code;
    private String result;
    private String message;
    private List<CategoryDto> data;

    public ApiResponse(int code, String result, String message, List<CategoryDto> data) {
        this.code = code;
        this.result = result;
        this.message = message;
        this.data = data;
    }

    // Getters
    public int getCode() {
        return code;
    }

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public List<CategoryDto> getData() {
        return data;
    }
}

