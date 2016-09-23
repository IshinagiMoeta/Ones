package com.bystanders.moeta.ones.bean;

import java.util.List;

/**
 * 广告轮播的Data
 * Created by Ishinagi_moeta on 2016/9/20.
 */
public class ReadingBroadCastBean {


    /**
     * res : 0
     * data : [{"id":"105","title":"要做你自己，因为没人想做你","cover":"http://image.wufazhuce.com/FgA7ELCwoaD7NBJcpyKsF_jxdIS9","bottom_text":"\u201c我要有能做我自己的自由， 和敢做我自己的胆量。\u201d","bgcolor":"#fc553b","pv_url":"http://v3.wufazhuce.com:8000/api/reading/carousel/pv/105"},{"id":"104","title":"我的梦想就是成为你的梦想","cover":"http://image.wufazhuce.com/FtKBspBPdeI80gd-KZHX3a1YYlVx","bottom_text":"梦想还是要有的，万一实现了呢。","bgcolor":"#1b4692","pv_url":"http://v3.wufazhuce.com:8000/api/reading/carousel/pv/104"},{"id":"103","title":"我在三十岁的第一年","cover":"http://image.wufazhuce.com/Fj7GbP80kOaUbWLAX4QeX4EyLn8H","bottom_text":"三十啊，你自己的感觉不重要，但是，赢很重要。","bgcolor":"#35221b","pv_url":"http://v3.wufazhuce.com:8000/api/reading/carousel/pv/103"},{"id":"97","title":"毛利文章精选","cover":"http://image.wufazhuce.com/FuMLnn4LaZq25fR5St3QQX4gfPYI","bottom_text":"失败者才不管别的有多重要。任性一回，不然一辈子都憋屈。","bgcolor":"#2e2e2e","pv_url":"http://v3.wufazhuce.com:8000/api/reading/carousel/pv/97"},{"id":"100","title":"你看上去好讨厌哦","cover":"http://image.wufazhuce.com/Fs_pmdYjetPtBgVUup5jqc57Wh0g","bottom_text":"无可避免的，我们最终成了我们讨厌的人。","bgcolor":"#727171","pv_url":"http://v3.wufazhuce.com:8000/api/reading/carousel/pv/100"},{"id":"99","title":"昨天不要回头，明天要到白首","cover":"http://image.wufazhuce.com/FnrA93aoIGcM4KeqzxgYNGTvtQAV","bottom_text":"最感人的戏份儿，不是极尽绚烂的演出，而是相爱之人的真情流露。 by 关熙潮","bgcolor":"#52a8b3","pv_url":"http://139.129.116.86:8000/api/reading/carousel/pv/99"},{"id":"93","title":"when can I see u again","cover":"http://image.wufazhuce.com/Ft-qdMcLzjIWYp0ez_gaF-East7p","bottom_text":"\u201c千万别对自己残忍，快乐的时候就去快乐，今朝有酒今朝醉，别浪费了好时光去思考未来何时伤心掉眼泪。\u201d","bgcolor":"#2f2f37","pv_url":"http://139.129.116.86:8000/api/reading/carousel/pv/93"}]
     */

    private int res;
    /**
     * id : 105
     * title : 要做你自己，因为没人想做你
     * cover : http://image.wufazhuce.com/FgA7ELCwoaD7NBJcpyKsF_jxdIS9
     * bottom_text : “我要有能做我自己的自由， 和敢做我自己的胆量。”
     * bgcolor : #fc553b
     * pv_url : http://v3.wufazhuce.com:8000/api/reading/carousel/pv/105
     */

    private List<DataBean> data;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String title;
        private String cover;
        private String bottom_text;
        private String bgcolor;
        private String pv_url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getBottom_text() {
            return bottom_text;
        }

        public void setBottom_text(String bottom_text) {
            this.bottom_text = bottom_text;
        }

        public String getBgcolor() {
            return bgcolor;
        }

        public void setBgcolor(String bgcolor) {
            this.bgcolor = bgcolor;
        }

        public String getPv_url() {
            return pv_url;
        }

        public void setPv_url(String pv_url) {
            this.pv_url = pv_url;
        }
    }
}
