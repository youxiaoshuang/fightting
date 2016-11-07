package com.youdows.fightting.weixin.pull;

import lombok.Data;

@Data
public class MessageCustomSend {
    private String touser;
    private String msgtype;
    private Content text;
}
