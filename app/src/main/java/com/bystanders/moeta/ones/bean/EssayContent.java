package com.bystanders.moeta.ones.bean;

import java.util.List;

/**
 * 短篇的JSON解析数据
 * Created by Ishinagi_moeta on 2016/9/22.
 */
public class EssayContent {

    private int res;
    /**
     * content_id : 1536
     * hp_title : 断电人生
     * sub_title :
     * hp_author : 李维北
     * auth_it : 李维北，青年作者。新书《用你的名字写个故事》即将上市。
     * hp_author_introduce : （责任编辑：卫天成 weitiancheng@wufazhuce.com）
     * hp_makettime : 2016-09-21 23:00:00
     * wb_name :
     * wb_img_url :
     * last_update_date : 2016-09-21 16:00:14
     * web_url : http://m.wufazhuce.com/article/1536
     * guide_word : 黑暗中一点点光亮都会特别显眼，当靠近光点时你才发现，身旁原来还有其他人，只是大家都一直看着前面，忘记了彼此的存在。
     * audio : http://music.wufazhuce.com/lnFmFukHZV6EUBExF_MuqSuoanMB
     * author : [{"user_id":"4814795","user_name":"李维北","web_url":"http://image.wufazhuce.com/FtYBiYEg2U6ggmLKgBZOsTKRqmJG","desc":"李维北，青年作者。新书《用你的名字写个故事》即将上市。","wb_name":"@李维北"}]
     * praisenum : 988
     * sharenum : 315
     * commentnum : 213
     */

    private DataBean data;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String content_id;
        private String hp_title;
        private String sub_title;
        private String hp_author;
        private String auth_it;
        private String hp_author_introduce;
        private String hp_content;
        private String hp_makettime;
        private String wb_name;
        private String wb_img_url;
        private String last_update_date;
        private String web_url;
        private String guide_word;
        private String audio;
        private int praisenum;
        private int sharenum;
        private int commentnum;
        /**
         * user_id : 4814795
         * user_name : 李维北
         * web_url : http://image.wufazhuce.com/FtYBiYEg2U6ggmLKgBZOsTKRqmJG
         * desc : 李维北，青年作者。新书《用你的名字写个故事》即将上市。
         * wb_name : @李维北
         */

        private List<AuthorBean> author;

        public String getContent_id() {
            return content_id;
        }

        public void setContent_id(String content_id) {
            this.content_id = content_id;
        }

        public String getHp_title() {
            return hp_title;
        }

        public void setHp_title(String hp_title) {
            this.hp_title = hp_title;
        }

        public String getSub_title() {
            return sub_title;
        }

        public void setSub_title(String sub_title) {
            this.sub_title = sub_title;
        }

        public String getHp_author() {
            return hp_author;
        }

        public void setHp_author(String hp_author) {
            this.hp_author = hp_author;
        }

        public String getAuth_it() {
            return auth_it;
        }

        public void setAuth_it(String auth_it) {
            this.auth_it = auth_it;
        }

        public String getHp_author_introduce() {
            return hp_author_introduce;
        }

        public void setHp_author_introduce(String hp_author_introduce) {
            this.hp_author_introduce = hp_author_introduce;
        }

        public String getHp_content() {
            return hp_content;
        }

        public void setHp_content(String hp_content) {
            this.hp_content = hp_content;
        }

        public String getHp_makettime() {
            return hp_makettime;
        }

        public void setHp_makettime(String hp_makettime) {
            this.hp_makettime = hp_makettime;
        }

        public String getWb_name() {
            return wb_name;
        }

        public void setWb_name(String wb_name) {
            this.wb_name = wb_name;
        }

        public String getWb_img_url() {
            return wb_img_url;
        }

        public void setWb_img_url(String wb_img_url) {
            this.wb_img_url = wb_img_url;
        }

        public String getLast_update_date() {
            return last_update_date;
        }

        public void setLast_update_date(String last_update_date) {
            this.last_update_date = last_update_date;
        }

        public String getWeb_url() {
            return web_url;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }

        public String getGuide_word() {
            return guide_word;
        }

        public void setGuide_word(String guide_word) {
            this.guide_word = guide_word;
        }

        public String getAudio() {
            return audio;
        }

        public void setAudio(String audio) {
            this.audio = audio;
        }

        public int getPraisenum() {
            return praisenum;
        }

        public void setPraisenum(int praisenum) {
            this.praisenum = praisenum;
        }

        public int getSharenum() {
            return sharenum;
        }

        public void setSharenum(int sharenum) {
            this.sharenum = sharenum;
        }

        public int getCommentnum() {
            return commentnum;
        }

        public void setCommentnum(int commentnum) {
            this.commentnum = commentnum;
        }

        public List<AuthorBean> getAuthor() {
            return author;
        }

        public void setAuthor(List<AuthorBean> author) {
            this.author = author;
        }

        public static class AuthorBean {
            private String user_id;
            private String user_name;
            private String web_url;
            private String desc;
            private String wb_name;

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getWeb_url() {
                return web_url;
            }

            public void setWeb_url(String web_url) {
                this.web_url = web_url;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getWb_name() {
                return wb_name;
            }

            public void setWb_name(String wb_name) {
                this.wb_name = wb_name;
            }
        }
    }
}
