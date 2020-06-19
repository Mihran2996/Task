package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Code {
    int id;
    int code;
    int userId;
    public Code(int code,int userId){
        this.code=code;
        this. userId=userId;
    }
}
