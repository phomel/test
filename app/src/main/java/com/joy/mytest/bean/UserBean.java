package com.joy.mytest.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/19.
 */
public class UserBean {

    /**
     * id : 2
     * username : test2
     * name : 业务员2
     * role : {"departId":0,"depart":{"id":11,"fdepart":{"id":10,"fdepart":{"id":2,"name":"业务部","account":"32424324213213","accountName":"账户名","openBank":"中国公司灰姑娘银行"},"name":"杭州业务部","province":"浙江省","city":"杭州市","county":"上城区","account":"32424324213213","accountName":"账户名","openBank":"中国公司灰姑娘银行"},"name":"杭州业务部1组","province":"浙江省","city":"杭州市","county":"江干区","account":"32424324213213","accountName":"账户名","openBank":"中国公司灰姑娘银行"},"name":"业务员","privileges":[{"id":1,"name":"征信申请"},{"id":2,"name":"客户信息录入修改"},{"id":5,"name":"通融申请"},{"id":8,"name":"资金预约"},{"id":9,"name":"提车"}],"id":5}
     * createDt : 2016-06-21 16:00:00
     */

    private User user;
    /**
     * errMsg : null
     * depart : {"id":0,"account":"32424324213213","accountName":"账户名","openBank":"中国公司灰姑娘银行"}
     * ok : 1
     * user : {"id":2,"username":"test2","name":"业务员2","role":{"departId":0,"depart":{"id":11,"fdepart":{"id":10,"fdepart":{"id":2,"name":"业务部","account":"32424324213213","accountName":"账户名","openBank":"中国公司灰姑娘银行"},"name":"杭州业务部","province":"浙江省","city":"杭州市","county":"上城区","account":"32424324213213","accountName":"账户名","openBank":"中国公司灰姑娘银行"},"name":"杭州业务部1组","province":"浙江省","city":"杭州市","county":"江干区","account":"32424324213213","accountName":"账户名","openBank":"中国公司灰姑娘银行"},"name":"业务员","privileges":[{"id":1,"name":"征信申请"},{"id":2,"name":"客户信息录入修改"},{"id":5,"name":"通融申请"},{"id":8,"name":"资金预约"},{"id":9,"name":"提车"}],"id":5},"createDt":"2016-06-21 16:00:00"}
     * token : MLvqbN7vN3jwB4XolgIBTA==
     */

    private String token;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class User {
        private int id;
        private String username;
        private String name;
        /**
         * departId : 0
         * depart : {"id":11,"fdepart":{"id":10,"fdepart":{"id":2,"name":"业务部","account":"32424324213213","accountName":"账户名","openBank":"中国公司灰姑娘银行"},"name":"杭州业务部","province":"浙江省","city":"杭州市","county":"上城区","account":"32424324213213","accountName":"账户名","openBank":"中国公司灰姑娘银行"},"name":"杭州业务部1组","province":"浙江省","city":"杭州市","county":"江干区","account":"32424324213213","accountName":"账户名","openBank":"中国公司灰姑娘银行"}
         * name : 业务员
         * privileges : [{"id":1,"name":"征信申请"},{"id":2,"name":"客户信息录入修改"},{"id":5,"name":"通融申请"},{"id":8,"name":"资金预约"},{"id":9,"name":"提车"}]
         * id : 5
         */

        private RoleBean role;
        private String createDt;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public RoleBean getRole() {
            return role;
        }

        public void setRole(RoleBean role) {
            this.role = role;
        }

        public String getCreateDt() {
            return createDt;
        }

        public void setCreateDt(String createDt) {
            this.createDt = createDt;
        }

        public static class RoleBean {
            private int departId;
            private String name;
            private int id;
            /**
             * id : 1
             * name : 征信申请
             */

            private List<PrivilegesBean> privileges;

            public int getDepartId() {
                return departId;
            }

            public void setDepartId(int departId) {
                this.departId = departId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public List<PrivilegesBean> getPrivileges() {
                return privileges;
            }

            public void setPrivileges(List<PrivilegesBean> privileges) {
                this.privileges = privileges;
            }

            public static class PrivilegesBean {
                private int id;
                private String name;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}
