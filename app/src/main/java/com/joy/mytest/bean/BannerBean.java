package com.joy.mytest.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/19.
 */
public class BannerBean {

    /**
     * ok : 1
     * list : [{"img":"http://www.huaxur.com/static_carloan/upload/1477294839754.png","id":4,"title":"通知公告1"},{"img":"http://www.huaxur.com/static_carloan/upload/1477296244291.png","id":7,"title":"公告2"}]
     */

    private int ok;
    /**
     * img : http://www.huaxur.com/static_carloan/upload/1477294839754.png
     * id : 4
     * title : 通知公告1
     */

    private List<Image> list;

    public int getOk() {
        return ok;
    }

    public void setOk(int ok) {
        this.ok = ok;
    }

    public List<Image> getList() {
        return list;
    }

    public void setList(List<Image> list) {
        this.list = list;
    }

    public static class Image {
        private String img;
        private int id;
        private String title;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
