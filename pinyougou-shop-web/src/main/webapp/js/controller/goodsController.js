//控制层
app.controller('goodsController', function ($scope, $controller, goodsService, uploadService, itemCatService, typeTemplateService) {

    $controller('baseController', {$scope: $scope});//继承

    //读取列表数据绑定到表单中  
    $scope.findAll = function () {
        goodsService.findAll().success(
            function (response) {
                $scope.list = response;
            }
        );
    }

    //分页
    $scope.findPage = function (page, rows) {
        goodsService.findPage(page, rows).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }

    //查询实体
    $scope.findOne = function (id) {
        goodsService.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        );
    }

    //保存
    $scope.save = function () {
        var serviceObject;//服务层对象
        if ($scope.entity.id != null) {//如果有ID
            serviceObject = goodsService.update($scope.entity); //修改
        } else {
            serviceObject = goodsService.add($scope.entity);//增加
        }
        serviceObject.success(
            function (response) {
                if (response.success) {
                    //重新查询
                    $scope.reloadList();//重新加载
                } else {
                    alert(response.message);
                }
            }
        );
    }

    //新增
    $scope.add = function () {

        // 提取kindeditor编辑器的内容
        $scope.entity.goodsDesc.introduction = editor.html();

        goodsService.add($scope.entity).success(
            function (response) {
                if (response.success) {
                    alert('保存成功');
                    $scope.entity = {};
                    editor.html('');//清空富文本编辑器
                } else {
                    alert(response.message);
                }
            }
        );
    }


    //批量删除
    $scope.dele = function () {
        //获取选中的复选框
        goodsService.dele($scope.selectIds).success(
            function (response) {
                if (response.success) {
                    $scope.reloadList();//刷新列表
                    $scope.selectIds = [];
                }
            }
        );
    }

    $scope.searchEntity = {};//定义搜索对象

    //搜索
    $scope.search = function (page, rows) {
        goodsService.search(page, rows, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    };

    /**
     * 上传图片
     */
    $scope.uploadFile = function () {
        uploadService.uploadFile().success(function (response) {
            if (response.success) {//如果上传成功，取出url
                $scope.image_entity.url = response.message;//设置文件地址
            } else {
                alert(response.message);
            }
        }).error(function () {
            alert("上传发生错误");
        });
    };

    //定义页面实体结构
    $scope.entity = {goods: {}, goodsDesc: {itemImages: [], specificationItems: []}};

    //添加图片列表
    $scope.add_image_entity = function () {
        $scope.entity.goodsDesc.itemImages.push($scope.image_entity);
    };

    //列表中移除图片
    $scope.remove_image_entity = function (index) {
        $scope.entity.goodsDesc.itemImages.splice(index, 1);
    };

    // 查询商品分类列表，第一级
    $scope.selectItemCat1List = function () {
        itemCatService.findByParentId(0).success(function (response) {
            $scope.itemCat1List = response;
        });
    };

    // 查询商品分类列表，第二级，根据category1Id的值的变化触发
    $scope.$watch('entity.goods.category1Id', function (newValue, oldValue) {

        //根据选择的值，查询二级分类
        itemCatService.findByParentId(newValue).success(
            function (response) {
                $scope.itemCat2List = response;
            }
        );

    });

    // 查询商品分类列表，第三级，根据category2Id的值的变化触发
    $scope.$watch('entity.goods.category2Id', function (newValue, oldValue) {
        //根据选择的值，查询三级分类
        itemCatService.findByParentId(newValue).success(
            function (response) {
                $scope.itemCat3List = response;
            }
        );
    });

    //三级分类选择后  读取模板ID
    $scope.$watch('entity.goods.category3Id', function (newValue, oldValue) {
        itemCatService.findOne(newValue).success(
            function (response) {
                $scope.entity.goods.typeTemplateId = response.typeId; //更新模板ID
            }
        );
    });

    //模板ID选择后  更新品牌列表
    $scope.$watch('entity.goods.typeTemplateId', function (newValue, oldValue) {
        typeTemplateService.findOne(newValue).success(
            function (response) {
                $scope.typeTemplate = response;//获取类型模板
                $scope.typeTemplate.brandIds = JSON.parse($scope.typeTemplate.brandIds);//品牌列表
                $scope.entity.goodsDesc.customAttributeItems = JSON.parse($scope.typeTemplate.customAttributeItems);//扩展属性
            }
        );

        //查询规格列表
        typeTemplateService.findSpecList(newValue).success(
            function (response) {
                $scope.specList = response;
            }
        );
    });

    // 更新规格属性值
    // 即：$scope.entity={ goodsDesc:{itemImages:[],specificationItems:[]}}
    // 中的specificationItems，例子：
    // [{"attributeName":"网络制式","attributeValue":["移动3G","移动4G"]},{"attributeName":"屏幕尺寸","attributeValue":["6寸","5.5寸"]}]
    $scope.updateSpecAttribute = function ($event, name, value) {

        // 获得{"attributeName":"网络制式","attributeValue":["移动3G","移动4G"]}
        var object = $scope.searchObjectByKey($scope.entity.goodsDesc.specificationItems, 'attributeName', name);

        if (object != null) {

            // 如果找到了：{"attributeName":"网络制式","attributeValue":["移动3G","移动4G"]}
            // 就判断他是否是勾选的
            if ($event.target.checked) {

                // 如果是勾选的
                // 就往{"attributeName":"网络制式","attributeValue":["移动3G","移动4G"]}
                // 里面的attributeValue属性，继续添加值
                object.attributeValue.push(value);
            } else {

                // 如果是取消勾选
                // 就将{"attributeName":"网络制式","attributeValue":["移动3G","移动4G"]}
                // 里面的attributeValue属性中的，具体的某个值去除
                object.attributeValue.splice(object.attributeValue.indexOf(value), 1);//移除选项

                //如果选项都取消了，将此条记录移除
                if (object.attributeValue.length == 0) {
                    // 将[{"attributeName":"网络制式","attributeValue":[]},{"attributeName":"屏幕尺寸","attributeValue":["6寸","5.5寸"]}]
                    // 里面的{"attributeName":"网络制式","attributeValue":[]}移除
                    $scope.entity.goodsDesc.specificationItems.splice($scope.entity.goodsDesc.specificationItems.indexOf(object), 1);
                }
            }
        } else {
            // 如果中的specificationItems中没有所需查找的对象，如没有：{"attributeName":"网络制式","attributeValue":["移动3G","移动4G"]}
            // 就新建一个：{"attributeName":"网络制式","attributeValue":["移动3G","移动4G"]}
            $scope.entity.goodsDesc.specificationItems.push({"attributeName": name, "attributeValue": [value]});
        }
    }

    //创建SKU列表
    // 思路：
    // （1）我们先定义一个初始的不带规格名称的集合，只有一条记录。
    // （2）循环用户选择的规格，根据规格名称和已选择的规格选项对原集合进行扩充，添加规格名称和值，新增的记录数与选择的规格选项个数相同

    $scope.createItemList = function () {

        // (1) 我们先定义一个初始的不带规格名称的集合，只有一条记录。这个代表sku的一行数据
        $scope.entity.itemList = [{spec: {}, price: 0, num: 99999, status: '0', isDefault: '0'}];

        // (2) 循环用户选择的规格，根据规格名称和已选择的规格选项对原集合进行扩充
        // 例子数据：$scope.entity.goodsDesc.specificationItems如下：
        // [{"attributeName":"网络","attributeValue":["双卡","电信2G","联通2G"]},{"attributeName":"机身内存","attributeValue":["128G","64G"]}]
        var items = $scope.entity.goodsDesc.specificationItems;

        for (var i = 0; i < items.length; i++) {

            // 一次循环，取出一组数据：
            // 例子如下：items[i]如下：
            // {"attributeName":"网络","attributeValue":["双卡","电信2G","联通2G"]}
            $scope.entity.itemList = addColumn($scope.entity.itemList, items[i].attributeName, items[i].attributeValue);
        }
    }

    //添加列值
    // list：就是多行sku数据
    // columnName：{"attributeName":"网络","attributeValue":["双卡","电信2G","联通2G"]}中的attributeName，即：网络
    // conlumnValues：{"attributeName":"网络","attributeValue":["双卡","电信2G","联通2G"]}中的attributeValue，即：
    // ["双卡","电信2G","联通2G"]
    addColumn = function (list, columnName, conlumnValues) {
        var newList = [];//新的集合

        // 遍历当前sku的数据有多少行
        // 例如：[{spec: {}, price: 0, num: 99999, status: '0', isDefault: '0'}]
        // 就只有一行
        for (var i = 0; i < list.length; i++) {

            // 取出{spec: {}, price: 0, num: 99999, status: '0', isDefault: '0'}
            var oldRow = list[i];

            // 遍历
            // {"attributeName":"网络","attributeValue":["双卡","电信2G","联通2G"]}
            // 中的attributeValue，即：["双卡","电信2G","联通2G"]
            for (var j = 0; j < conlumnValues.length; j++) {

                // 深克隆：把一个对象的值，完整克隆过来，两个对象指向不用引用
                // 浅克隆：把一个对象的引用赋值个另一个对象。两个对象指向同一个引用

                // 进行深克隆
                // JSON.stringify把对象转成json，得到一个json值
                // JSON.parse重新把json转成一个对象。
                // 如此形成深克隆
                // 此时的newRow:
                // {spec: {}, price: 0, num: 99999, status: '0', isDefault: '0'}
                var newRow = JSON.parse(JSON.stringify(oldRow));

                // 添加值
                // 循环完成后形成：{spec: {[{"网络": ["双卡","电信2G","联通2G"]},...]}, price: 0, num: 99999, status: '0', isDefault: '0'}格式
                newRow.spec[columnName] = conlumnValues[j];
                newList.push(newRow);
            }
        }

        return newList;
    }


});
