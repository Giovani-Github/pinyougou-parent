/**
 * 广告服务控制层
 * @Author Giovani
 * @Create: 2018/8/13 15:33
 */

app.controller("contentController", function ($scope, contentService) {
    $scope.contentList = [];//广告集合
    $scope.findByCategoryId = function (categoryId) {
        contentService.findByCategoryId(categoryId).success(
            function (response) {
                $scope.contentList[categoryId] = response;
            }
        );
    }
});
