package com.youdows.fightting.weixin.pull;

import lombok.Data;

/**
 * 消息内容
 */
@Data
public class Content {

    public Content() {
    }

    public Content(String content) {
        this.content = content;
    }

    private String content;
}
