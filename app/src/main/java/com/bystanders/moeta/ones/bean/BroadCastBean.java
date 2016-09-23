package com.bystanders.moeta.ones.bean;

import java.util.List;

/**
 * 广告轮播点击进入的详情页面的Data
 * Created by Ishinagi_moeta on 2016/9/21.
 */
public class BroadCastBean {

    /**
     * res : 0
     * data : [{"item_id":"734","title":"我没有成为自己不喜欢的样子","introduction":"我做得到的，我竭力做到最好；我做不到的，可能我永远也做不到。或许生命并不意味着成为了什么、做到了什么花好月圆，它原本就是这般的自在安然。","author":"严明","web_url":"","number":0,"type":"1"},{"item_id":"165","title":"你想对17岁的自己说什么？","author":"网友答@亭林镇独唱团：","introduction":"@曾经是文艺青年的Rect：17岁的你在几个月后会向一个姑娘表白。你必须珍惜那个姑娘。不要放弃她。因","web_url":"","number":0,"type":"3"},{"item_id":"342","title":"感谢五年前的自己","introduction":"感谢五年前的你，虽然错过、痛过、彷徨过、挣扎过，但是不管怎样\u2014\u2014这一切的苦难，你承受了，并且承受住了。","author":"廖智","web_url":"","number":0,"type":"1"},{"item_id":"1131","title":"如何成为自己最讨厌的那种人","introduction":"能完美地做到这六点，就能成为自己最讨厌的那种人。","author":"宋小君","web_url":"","number":0,"type":"1"},{"item_id":"568","title":"孤独时如何开导自己？","author":"@乔小囧答Qstellea：","introduction":"当你觉得孤独时，你得相信绝对有人，正藏在角落里偷偷爱你。","web_url":"","number":0,"type":"3"},{"item_id":"1451","title":"逃避是问题，是因为你并不真的了解自己","introduction":"你有过逃避的经历么？","author":"李松蔚","web_url":"","number":0,"type":"1"},{"item_id":"593","title":"在爱和旅行里，寻找不同的自己","introduction":"人生需要两次觉悟，说走就走你得有钱，奋不顾身你首先不能长得丑。","author":"张皓宸","web_url":"","number":0,"type":"1"},{"item_id":"1414","title":"都说要做自己，我们可以\u201c做别人\u201d么？  ","author":"@A-M-E-N-G 答Eteen：","introduction":"不久前微博在传王尔德的话：做你自己，因为别人都有人做了。 也不知是真王尔德说的还是假王尔德说的，我","web_url":"","number":0,"type":"3"},{"item_id":"1205","title":"怎样才算是\u201c爱自己\u201d？","author":"@曲玮玮 答铅笔有没有毒：","introduction":"爱自己，就是不因外界糟糕的环境而自暴自弃，不为别人的错误而惩罚自己。不要因为手中有一副烂牌，就向命运","web_url":"","number":0,"type":"3"}]
     */

    private int res;
    /**
     * item_id : 734
     * title : 我没有成为自己不喜欢的样子
     * introduction : 我做得到的，我竭力做到最好；我做不到的，可能我永远也做不到。或许生命并不意味着成为了什么、做到了什么花好月圆，它原本就是这般的自在安然。
     * author : 严明
     * web_url :
     * number : 0
     * type : 1
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
        private String item_id;
        private String title;
        private String introduction;
        private String author;
        private String web_url;
        private int number;
        private String type;

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getWeb_url() {
            return web_url;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
