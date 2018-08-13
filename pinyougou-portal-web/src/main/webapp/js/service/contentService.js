/**
 * 广告服务层
 * @Author Giovani
 * @Create: 2018/8/13 15:33
 */

app.service("contentService", function ($http) {
    //根据分类ID查询广告列表
    this.findByCategoryId = function (categoryId) {
        return $http.get("content/findByCategoryId.do?categoryId=" + categoryId);
    }
});
