package cn.cuit.microcollege.entity.HttpResultEntity;

import java.util.List;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/4
 * @Descirption:
 */
public class NewResultBean {

    /**
     * msg : success
     * result : {"curPage":1,"list":[{"cid":"2","hitCount":"2264","id":"7bf251d1a8bf70f5781d6380fe3ddcfc","pubTime":"2019-05-04","sourceUrl":"https://mini.eastday.com/a/190504190039476.html","subTitle":"安徽先锋网","thumbnails":"http://06imgmini.eastday.com/mobile/20190504/20190504190039_6af1683d006d3e8f36dbe60a75de3098_1_mwpl_05500201.jpg","title":"经开区临湖社区\u201c四个注重\u201d加强基层党建队伍建设"},{"cid":"2","hitCount":"4966","id":"423df5aaacb52743a706135fe7f780b1","pubTime":"2019-05-04","sourceUrl":"https://mini.eastday.com/a/190504190031514.html","subTitle":"黄河新闻网","thumbnails":"http://01imgmini.eastday.com/mobile/20190504/20190504190031_26008cb842b5b9b4f8f1401bd9bfe5d4_1_mwpl_05500201.jpg","title":"昔阳县城市提档升级攻坚行动持续推进"},{"cid":"2","hitCount":"4932","id":"51c3fc131994c797bea9802d12532482","pubTime":"2019-05-04","sourceUrl":"https://mini.eastday.com/a/190504190059048.html","subTitle":"千龙网","thumbnails":"http://03imgmini.eastday.com/mobile/20190504/20190504190059_76b379996639207a26553d9982aa2f14_4_mwpl_05500201.jpg","title":"2019国际当代景观种植设计大师峰会在京举办"}],"total":362610}
     * retCode : 200
     */

    private String msg;
    private ResultBean result;
    private String retCode;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public static class ResultBean {
        /**
         * curPage : 1
         * list : [{"cid":"2","hitCount":"2264","id":"7bf251d1a8bf70f5781d6380fe3ddcfc","pubTime":"2019-05-04","sourceUrl":"https://mini.eastday.com/a/190504190039476.html","subTitle":"安徽先锋网","thumbnails":"http://06imgmini.eastday.com/mobile/20190504/20190504190039_6af1683d006d3e8f36dbe60a75de3098_1_mwpl_05500201.jpg","title":"经开区临湖社区\u201c四个注重\u201d加强基层党建队伍建设"},{"cid":"2","hitCount":"4966","id":"423df5aaacb52743a706135fe7f780b1","pubTime":"2019-05-04","sourceUrl":"https://mini.eastday.com/a/190504190031514.html","subTitle":"黄河新闻网","thumbnails":"http://01imgmini.eastday.com/mobile/20190504/20190504190031_26008cb842b5b9b4f8f1401bd9bfe5d4_1_mwpl_05500201.jpg","title":"昔阳县城市提档升级攻坚行动持续推进"},{"cid":"2","hitCount":"4932","id":"51c3fc131994c797bea9802d12532482","pubTime":"2019-05-04","sourceUrl":"https://mini.eastday.com/a/190504190059048.html","subTitle":"千龙网","thumbnails":"http://03imgmini.eastday.com/mobile/20190504/20190504190059_76b379996639207a26553d9982aa2f14_4_mwpl_05500201.jpg","title":"2019国际当代景观种植设计大师峰会在京举办"}]
         * total : 362610
         */

        private int curPage;
        private int total;
        private List<ListBean> list;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * cid : 2
             * hitCount : 2264
             * id : 7bf251d1a8bf70f5781d6380fe3ddcfc
             * pubTime : 2019-05-04
             * sourceUrl : https://mini.eastday.com/a/190504190039476.html
             * subTitle : 安徽先锋网
             * thumbnails : http://06imgmini.eastday.com/mobile/20190504/20190504190039_6af1683d006d3e8f36dbe60a75de3098_1_mwpl_05500201.jpg
             * title : 经开区临湖社区“四个注重”加强基层党建队伍建设
             */

            private String cid;
            private String hitCount;
            private String id;
            private String pubTime;
            private String sourceUrl;
            private String subTitle;
            private String thumbnails;
            private String title;

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getHitCount() {
                return hitCount;
            }

            public void setHitCount(String hitCount) {
                this.hitCount = hitCount;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPubTime() {
                return pubTime;
            }

            public void setPubTime(String pubTime) {
                this.pubTime = pubTime;
            }

            public String getSourceUrl() {
                return sourceUrl;
            }

            public void setSourceUrl(String sourceUrl) {
                this.sourceUrl = sourceUrl;
            }

            public String getSubTitle() {
                return subTitle;
            }

            public void setSubTitle(String subTitle) {
                this.subTitle = subTitle;
            }

            public String getThumbnails() {
                return thumbnails;
            }

            public void setThumbnails(String thumbnails) {
                this.thumbnails = thumbnails;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
