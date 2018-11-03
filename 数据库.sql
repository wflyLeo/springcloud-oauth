/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.10.11
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : 192.168.10.11:3306
 Source Schema         : newretail_cloud

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 03/11/2018 20:37:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for newretail_sys_authority
-- ----------------------------
DROP TABLE IF EXISTS `newretail_sys_authority`;
CREATE TABLE `newretail_sys_authority`  (
  `sys_role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `sys_resource_id` bigint(20) NULL DEFAULT NULL COMMENT '资源ID',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 514 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '权限-资源角色关联表';

-- ----------------------------
-- Records of newretail_sys_authority
-- ----------------------------
BEGIN;
INSERT INTO `newretail_sys_authority` VALUES (4, 1, 477), (4, 2, 478), (4, 3, 479), (4, 4, 480), (4, 5, 481), (4, 6, 482), (4, 7, 483), (4, 8, 484), (4, 9, 485), (4, 10, 486), (4, 11, 487), (4, 12, 488), (4, 13, 489), (4, 14, 490), (4, 15, 491), (4, 16, 492), (4, 20, 493), (4, 21, 494), (4, 22, 495), (4, 17, 496), (4, 19, 497), (4, 18, 498), (4, 25, 499), (4, 26, 500), (4, 27, 501), (4, 28, 502), (4, 29, 503), (4, 30, 504), (4, 31, 505);
COMMIT;

-- ----------------------------
-- Table structure for newretail_sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `newretail_sys_resource`;
CREATE TABLE `newretail_sys_resource`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限的标识',
  `parent` bigint(20) NULL DEFAULT NULL COMMENT '父id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限请求路径',
  `icon` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '图标',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT 'Y' COMMENT '是否可用：Y-可用；N-不可用',
  `load_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '1:运营后台;2:店家后台',
  `type` int(11) NULL DEFAULT NULL,
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `service_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `route` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `is_parents` tinyint(1) NULL DEFAULT NULL COMMENT '是否父节点',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `IDX_RESOURCE_CODE`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '资源表';

-- ----------------------------
-- Records of newretail_sys_resource
-- ----------------------------
BEGIN;
INSERT INTO `newretail_sys_resource` VALUES (1, NULL, 0, '首页', '/index/home', 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAYAAAByDd+UAAAAAXNSR0IArs4c6QAAAd9JREFUSA3tlDFLA0EUhC9BCILERkiRgJAmjW0q63T+gHR2Vmn9BSmtLCwsbdLZia2VWAh2KQQLC7URRVAEET2/2eyG3O4ld5dYZmB8u+/NzDMXzygqiDiO6/DKsl7QXkzOkha8hw46t4ql5FQT3IbPdtMlVRTUa+eMyScjsAPfoXAGVy11FjTr5EvLUBHUhV9QOIErzqKz7VGMputmc1VCevBXaeAAlvwg9eyMEv/Anq/JdcfYVwLQwv0skzRWS4n7WfrxHHEZHssFvuHueJhxkNZ6KCajPNOCqAJPpQafcGemIWUoj/VSTFYlRRZFDKvwAgqvcDtVmKMpr82gmMxqwkazBm80BY9wKyGY46IMm0Ux2TUTw6UJ79QFt3BzjvxUi7JsJsXsaOpRDnUD13Aj1blAU5k2mxIP9Q4dkrcOe6VS6WOB7KlWdqwxPIJvgUi/hhAMCjZGKWHO7Pek4JI88uXCxFPie2nAAXyy1LmREBW9EGDg+2hq2ctomvipXrDUKfyc4D5NSF+fRjiHWi7qLAz8oFE7/Cv1dfpHYOAPaOoxCuNPo7PpMEvRm5HfD+42IHgP/6u/fC2CR75oI+2RPijUfWeuZi1yOlet3mRNetMW7iEIhJOmnGdlKCuBP+dCjnNKtfL5AAAAAElFTkSuQmCC', 1, 'N', '1', 1, '首页', NULL, NULL, 1), (2, NULL, 0, '门店管理', '/index/store', 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAYAAAByDd+UAAAAAXNSR0IArs4c6QAAAb5JREFUSA3tlj1LA0EQhr0gCgmCKGiVUrSyVAsLOysL8S/4UaSzsbMQ7PwB/gFLKxHsLLUSsQqpBAs/wCBIIJCQ85lzh+zNmYNLLlYZeDM77868s7u3uSQYMxaG4QHUIZgyU1nDLwpOgyC46FlIs02Qp3UQW/MbjvsB430Xn+HPzVzW8IiCPbAL7hPFrGQCfLvtLSYSMhKyM6f1ig8S5ZB6nLXEZB8EegXwDsRWVKKgA/yWG195XN9DLkuH4msnoNpdPVbxHK0lDDe67GAj9Lad5mNMCXLZTdTx9iLFcrMEaJVA02mXpVaPVLd8w1G0s4im5aLVYP7W5UQ9bMNcnp9ZhGr+bortzgP5grbAtEkeOESzDMTkaEvyvNaBfE9CcAeJy93kxk6CVWn4BD7BLFgCw7I3hKvRG4BdFQkWgCzgBIiJ1wsk/KWQ2A5QPiL++JD8Y8eLb4Eal6jpuK6juZosIjKIopIyVr6X9/Ntjt5Syw8tHjXM/WhHRzo60swn8O+XRt551uoQM+BBXmdu0v/X5fO2VmPNF610o0cFtKXZgCYaFdtNVxLjSZyDiP6DeBP601X1uLThC78OHzbhB0/6hc05x4OVAAAAAElFTkSuQmCC', 2, 'Y', '1', 1, '门店管理', NULL, NULL, 1), (3, NULL, 2, '小票设置', '/index/store/ticket', NULL, 1, 'Y', '1', 1, '小票设置', NULL, NULL, 0), (4, NULL, 2, '门店分类', '/index/store/classify', NULL, 2, 'Y', '1', 1, '门店分类', NULL, NULL, 0), (5, NULL, 2, '入驻审核', '/index/store/admission', NULL, 3, 'Y', '1', 1, '入驻审核', NULL, NULL, 0), (6, NULL, 2, '门店列表', '/index/store/listing', NULL, 4, 'Y', '1', 1, '门店列表', NULL, NULL, 0), (7, NULL, 0, '标签管理', '/index/label', 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAYAAAByDd+UAAAAAXNSR0IArs4c6QAAAiJJREFUSA3dlk1LW1EQhnNNXYjgossWBX+BQhcBC62/oEvjBzZSquCiIBayKnTdLoRC3IhoF/0BpYK4EKzozoVIIQtBQQiC1IUELKWkpM/czCSnanLuTYwLB947Z+acmeee3I/cRKJNVi6XZ9E5yqNUmzCVtgCyyLUiwVBboDQ2WInxKFpGYgJNBUYleMw4jXos5/HbQRBsuWvokSX+oLk55j+RE8YSeo3y4RzJblRAcewkLNYDhbYz63HMoE+m8ZOaPHug6wfwssNDtKo5n9uxBTSznf0lJzuZQs/Rd+YW8R+R2LvwSHJYz2AjTMQ4CExr5ZqNSClefjGBuTZfbUu2KSB112DWlLlph1aD6RnFBnpgE8zLjsX+hzUDpEmjnTWGxQW2DIsDBDaDxKo3iNSLkfPvrLI0XOy9hjTsRKdIbNxqY8O0IAowXWGV96LCWN/rrq2OmYgC3FXgSyskzqAb70bydoIZWy++ww3qjSkeZO4p+om+Eb9C+4w/oyR6y3tzAe9avwbmw9Bebe7Cm8ZvNPkbf4QealzAvwe2orHXeYHsRJqPaSe7JtvEOfQVWEnnIjkvkC7PUBf6hb6gHJAf+KYsCnCdzi/QDqCLpihOkRcI5A/r15yaloaR7tKWCFeK7z/QrmFRd/6ExyD2v/6VX81Ce+Ctt+XDl3cS0Ca6bZMPs0c1UiIRWMCEvKLkYyrqZ6KV1vNydx9wl1/WW3An+X9+M7pxsrmnIQAAAABJRU5ErkJggg==', 3, 'Y', '1', 1, '标签管理', NULL, NULL, 1), (8, NULL, 7, '会员标签', '/index/label/melable', NULL, 1, 'Y', '1', 1, '会员标签', NULL, NULL, 0), (9, NULL, 7, '商品标签', '/index/label/golable', NULL, 2, 'Y', '1', 1, '商品标签', NULL, NULL, 0), (10, NULL, 0, '商品管理', '/index/goods', 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAeCAYAAAA/xX6fAAAAAXNSR0IArs4c6QAAAnpJREFUSA3tlr1PFFEUxR0XTSShMAEbE4gUBBttSEjwo5KWhkItpLaypKCwsDDB2NHY2ECjpRV/gSHRUKiJMRBDYkKjRi1QjHyMv/P23tl5s8MyI2ulNzn77jv33PP2vdnZmeRYh0jTdIDyWdDoIMuX9phsJknyKU8emrPQOHgJ/jRe0DhetlBSJBHOwt0H2tUOeAd+gSpxEtEoOAG02zl2+4CxPFhsEuyDHXAXnCpXHsyqx3rlIa/JUjWFHrAJFHdKRTVIeQSnpmdPWyvFCRO8YWw76raGQwh5AHkpJlx+3BPGa5Yvc+4SNcAz8A3czOlKU2lMq56GPBAum9i9W72IFoFiRizjSJg1P1ZayvIM2UpOP2IeM8Yteld+h2eM9HvoPfPPxq15Q4fRNepRr8K93LvJ6pNvsmrfZsxZ5lPG/WS84nxxVA1Io5jyOvlYYPB2LhspfLDiUEaSwM0bv8f4GFwF/Qbl4lRTzBd6hwKLd54POYVtK0b3Hpx+PPfAD6uXDapJE/0FMtc9qdiOFoToC3SabkWF3IT6IFgAb8F3g3JxgzlplFLbAoq+rMBkOFBpupGRXUrw3TDvYVn6r1RPBYX/qpqz7ny6Z1ijuODH7qwRubhntKDfJ/5too4jTtwzrFHcoRePuEbU7p7RDv9fw+iMak78SP+xa+hP+N6ax1VF7p5hDb8t1q3zchWHmhr39DXCI2ja/u++MJ6vaXigXF5AnoppCf0o9dx7wvw60DvoI/Ac6M98H9QJndo5cAncBnpXfco7zg3GVrBgL3gIdkG3Ql7y9OvY2qEvTfEi+S1wAZwG2SmQVwm9rX0Fr8ESO3tVpemvaX4D6suqlJF7syMAAAAASUVORK5CYII=', 4, 'Y', '1', 1, '商品管理', NULL, NULL, 1), (11, NULL, 10, '商品分类', '/index/goods/goodClass', NULL, 1, 'Y', '1', 1, '商品分类', NULL, NULL, 0), (12, NULL, 10, '商品品牌', '/index/goods/goodBrand', NULL, 2, 'Y', '1', 1, '商品品牌', NULL, NULL, 0), (13, NULL, 10, '商品参数', '/index/goods/goodParam', NULL, 3, 'Y', '1', 1, '商品参数', NULL, NULL, 0), (14, NULL, 10, '商品单位', '/index/goods/goodUnit', NULL, 4, 'Y', '1', 1, '商品单位', NULL, NULL, 0), (15, NULL, 10, '商品列表', '/index/goods/goodList', NULL, 5, 'Y', '1', 1, '商品列表', NULL, NULL, 0), (16, NULL, 10, '商品审核', '/index/goods/goodAudi', NULL, 6, 'Y', '1', 1, '商品审核', NULL, NULL, 0), (17, NULL, 0, '会员管理', '/index/member', 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAYAAAByDd+UAAAAAXNSR0IArs4c6QAAAsdJREFUSA2dlj9oFUEQxnMqSORJioCIWhgRbdK8UkFfpZBCSRGsbIQ0IgRsLAQLCy2FSBDsrKyCGDTYxSJCShEUEsRCJCkkCCLiH4zn79vbCfvO3cve++B7szvzzcy7vb29K4YyUJZlF9kkPAMPw0NQ2IDrcBk+LYriNXZw0GgKrsJcSDvVuiNJY3Al6PKJ8RycgCdgx1Nj+RSTxqDcsazGCHtwEwrrcBru3ilZGq9VjqAavcY8CeBvKCzA/Y0JkaByfC7G1Yo3JahltCubZbwrUi/LpVx4Hwqq+f/y4rR7pisbuJn9I9WAqiWsmN9ZHNqNgta/9TL2FQsmquVrYoLdy8S2/nSgtz9zjvg7ZTRgi9gyPBbJ16YTVq1gt5q7bd23G/GfhN99PMe8RzQcNmWu3WuPTHcPQZ0gwgInxVY13P59wGgffAuvwBLGoNvwDB6Ht+BN6KCaNFxgcg1ODjFZgsJEJal+mZ933rL8iz0dxmJjNNe9Xo/VaKhhrsNBWFLDNTfkBKmJ7nj/q9CfGqMdht98zoVQh08nkrCmhibq1ER2hRJeDGOxMZq7EoJfsH6FHRehV7KhiiJ64oWfsQdjjbzuLHHdK+FGSuf8CKJL6guNEt9QFfACFvVi+EbgRyi8hM2HBoLoprHCxPUcauMIM+Y3i++xi5TlF+wR8yctots+YS4lIn7Pa35gx03H+LL3y1wyf6NFmHzwLRHNXvgGCrKaH4VfofAIamPowW/CoquJInm0BU3H0ekKhVmoo0z4AHVm5jR8bg2zDm+KzsAQf5icsj/VypK44+sJTQEfQm2in/BqqyahmOTsFzDaA3Cklp+zpNU9tESK9OBAnxjk5TSs7qE1lPVNN7GCXshZH1FhjdZjmmh57Z4ydO+zvs/E1kVzEmik3WuPjBpvo55PIGdJF/UCToKX5zzBeYqlPvWTuYlA+Q8KweOe0/eImgAAAABJRU5ErkJggg==', 7, 'Y', '1', 1, '会员管理', NULL, NULL, 1), (18, NULL, 17, '会员列表', '/index/member/memberList', NULL, 8, 'Y', '1', 1, '会员列表', NULL, NULL, 0), (19, NULL, 17, '会员等级', '/index/member/memberGrade', NULL, 0, 'Y', '1', 1, '会员等级', NULL, NULL, 0), (20, NULL, 0, '订单管理', '/index/order', 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAYAAAByDd+UAAAAAXNSR0IArs4c6QAAAVtJREFUSA3tljtOAzEURWOSLgUhKSIaKsp0oUCiyhKyhyyAlUTsIAvIJmiQ2EWKFANF+DQ0ERrOHY1HYxTbaBhZCHjSlZ+t973+zHQ6f0byPJ+DDLQtijm3RBqrsJihj8GzXWtpPCHOozHm1IlXtrVzFluYEHen2DbUkVVSjckT9kKdwYT2uAf/e9kxHzD0pXtE1GXYVxR67IpgotrZQ+ZrsAFdMAN7EJN1PQnGzh4GO8TxDrxS8TuOW/R7cAx8os7kE5eybKfDuFfc4nOHyQ/Nz0oIHUtwCwyYAj1TbxEsQ0Qn77Aqhqol/4emYqSpErz4UHxN4AkXf4F+jr4CsYu/wv7GV1AwIU5X4IJkXcYzcAm+4uNNWP8A61l6oroRYyEk+vbjTQwdxCFxi1zBarFREcWXQhUwf2EQGkvye5g8YZ3SB3gal5w3puyAo36iFNsVEiX5TXSz/sbZByZ6qdwqhc5hAAAAAElFTkSuQmCC', 5, 'Y', '1', 1, '订单管理', NULL, NULL, 1), (21, NULL, 20, '线下订单', '/index/order/offlineOrder', NULL, 1, 'Y', '1', 1, '线下订单', NULL, NULL, 0), (22, NULL, 20, '退货单', '/index/order/returnOrder', NULL, 2, 'Y', '1', 1, '退货单', NULL, NULL, 0), (23, NULL, 0, '统计分析', '/index/count', 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAYAAAByDd+UAAAAAXNSR0IArs4c6QAAAkNJREFUSA29lj9rFEEYxrPaRgM2QkLUKKSzOEgbrgmBYJMiHyFfSPEjhNReI2gRhBPTRbAISRH8x1mliekEOX+/vRmdW+eyu0dyDzw7M++87/vszr47O8VcAwyHww5u23AdLsFFKH7AAezDV0VRfKSdHgjtwBPYFPrutFYkaAUeJirf6b+EW3AVzgfa1+acPhHGrjQSxrELz6EYwF14uy5Yn+BrjDBH98o4HeAvKHrwzpUBmUljQixNmSsvyqTLGJ/sOf1bmXyNTMbCF1CY8//lxRjfmU82tVi8I3NAc4nDaC9bDFajcP1bL+NYsmRgrpCTJqleBrH0dxP/a+mS26ITJ2VCOp1yOCrr2mqcdBfkWICv4X7qw9jqjZ9Mx3flDiJ67BS/R912VxLeJeIt3IKraXTI2Qu27TmcD6DQuTWI8z19MAE4g8vVJNjcHMSBgqdllx2k6lg3Jm4evg/xn2kf5GKwuyOJUwUvyy7BOedow2cZfgm+1eYrhkfRt9oy542Jy0aCON6HcSUMTPGNweOqSDpmPgr+rF1SnO/BT1AcwYU0WZM+MX+X1Cr1fyaejJp/VxytvjfwKTyGm1TdBW1bxNwDBfsh+lkmyx62NXgGNxA7z/g0McXcfZd04ofP3Dvou3vYJGvOh9ixD7/0wZjd2rAX0FWYGsSPb21mwjjbzTuIzu73FARn+wMOol2WdzZHDAUFgop6LBA3e4gaSZaiLm98pwr7P7uZY2IUDU9r9cZPRuE61B6Ei1RgUh+Vazvq/wGt0M0/nLSQBwAAAABJRU5ErkJggg==', 6, 'Y', '1', 1, '统计分析', NULL, NULL, 1), (24, NULL, 0, '消息管理', '/index/news', 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAYAAAByDd+UAAAAAXNSR0IArs4c6QAAAppJREFUSA3NljtoFVEQhu9VCCIRkYCgRMSI0cpI8FH4RNNYpBIt7NNYpRYJiFhpwMZGQdAm0UYURCwUESGFWFgoaqFiE98k4oP4yPr9y8y95+496927keDAx8zO/DPnbO7Z3VQq82zVIuslSbIW3SDshzWwAmST8BJuwvVqtfoCX95YqBfGYRZamTTS9pZakcYhmAHZN7gEh2AddBqKlVNNGpl6htpalIZRdZqdx69sNUAakNZttFVPWkc9bB3f8YcLNQUi9YB6ZcNBqTlE0Ae/Qb/HwWZFsYx6bYZm9eV2UbwNsmJ/jtxJlYpmpJOYGZVR7DfBe/zSqKiNpGaAZsn6vXWBB/gDFo/xPE0H+VKhzRizZp9dn8UuJtK9JMneenZuEfP22cwJnxTeYbcln3vxH/hnNsNn10eyE3/IO+rZuUXM7LA7nPFJ4R1+tuQSL8rTcEOEuVico+sy7cemHhqegGx9WOR6Ks0mSU+YD2PqPaaZyuS3W/6B58M79Df9Fi+av2d+JJMPL73mWq8NWnDfEzXPTo7YbsZrSQJy+mL4Xd4i1snrMhQrJ5Om9qUgXgSvQbYjnJnGJFfBL/gBG0IB1wPwBvJMtYFMz1ETP8THv7sUzproDn5hZoA+SSNwF94ZipXrzGh3kfNTvzusNcSIlsFbkOkbF99ZQ1fjBT1a7BPITjVWI1eINsO01NixiCSaQrtYevgJsisQHspoX5pEeFwd2IV8VXqg9GBvhRMwCbJZOAnFFtMCiB+BLH3p4peDhj6GV8YHfNb0Pt7zt0021WjYZlP0W+rknoavlss6HYyncAbaW8hXpvEcyHTU/R8j/ZmuwU5Ybfhry1vLeYb5w0qY/h5X8ZvKTSvQxfDL8AUuwsYCLf+35A8USmG6GBv2zQAAAABJRU5ErkJggg==', 7, 'Y', '1', 1, '消息管理', NULL, NULL, 1), (25, NULL, 0, '注册协议', '/index/agreement', 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAYAAAByDd+UAAAAAXNSR0IArs4c6QAAARBJREFUSA1jZEAC////lwBypZGE/jAyMl4E8YFyxkji6My/QHUXgGoYgRJGaJJPgXIv0MTABhYANfwFYmTwHqQQKMCMLIiF/RmqjgOLHMjMApiFIBeBAVAQZLgAEJ+FiIDJz0DXOQLlmIG8k0ji6MxvQHV2QHVsQIljaJKgkPkAlBdEEQcqBgMUQSpw0M1looKZJBkxaiEohW6FxQMR9FZC4UvtIP1PyEK4PMz1cAEqMdDNpbYPCTpz1MLRVEowkYymUoJBRKkCgvkQGAejZSneUB55qZQFKTxAdRkjMAjwtT+RlJPExKwngRZdhIU3DWhwYxrkROR2qSaQ3w7EMiAJKoLHQLOqgO3S61Q0k3ijAApwrpSmN5HhAAAAAElFTkSuQmCC', 8, 'Y', '1', 1, '注册协议', NULL, NULL, 1), (26, NULL, 25, '注册协议', '/index/agreement/protocol', NULL, 1, 'Y', '1', 1, '注册协议', NULL, NULL, 0), (27, NULL, 0, '系统管理', '/index/system', 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAYAAAByDd+UAAAAAXNSR0IArs4c6QAAAz9JREFUSA21ll9ojlEcx9+NYReLLZrNn8Io5UJpbrakxtiFcsONRJQR5cKdyJ0sSW6wlX8lwo3wtrWWmlDTuLB2IbRc2EJNlGVMx+f7vOcc530873pef371eX+/8/39zu8873nP8zxvJpPCjDEl0AFjENp3Bt0wK0Wb9CU0bAlXSYiPpO1WmlRIw7i+wNZ1lASG1mp1l4+GCfNtWSaT15jCmdBFVlvVCXN9ZYqA+hlwjtJx/AAsi0/zC5JUfB02gOKN0I9ej58Ok5kWmkfBA9gLU2EFZNGTf18SJ0D2AeqhVwPsK0xEkTHtNPGGtsfqcl9s/Bq/Gp7acRfef7FoMsJWm9RWrpWIL4OzVv+GvwzLown2g3E1tMEnkPVAlZ2/kPi9RKwtnKfmw5FszIG8BAP0LbAkrodj8lWwC6bE9DVoutgfMF857bWsPOcys0noHBo7zhDfcnEhT80ouYsJ+Vq0CSiDX+eARXaA+51uE1ckTNa33Q7atlF4CVehLl6LVgruTBCa0/EaNVsPaiQbhEpXRKwnzU1IMh2Wza5WnvFdW6gzsT/M5cUkdbpkajLNJYkPSsR0grdBJdTBeZDpQrV9kRH3ScT2Oc17xArQKT0Dz0F23xcQMH4bqcasC3XF6O6bH3M5tJO2/g3+AuhA5S6IwB1dwsi0DYeDyTVWH3Za6Mltsvk7Treadik0PX2a9dvoRA7ADXgETzh1Y/jISNcQaLERdL9tuWzhT+bpDlgJDdBofZ+2RHYFoaCRL7ilBSclJOhT7hZ8lZD3EoXxQ5P8fPQzJglo9hlk1a6MuBYWB+P4bdHvckV7GneDrB10Iw+BTNs4J2zI2N34PU5Hy0Jay2pLdydU69kn6wV/P7pFQk++mAXvlWgyk3biVsEg6KSOwwuQNXA6H+fC//TJBRwC2W8LoellG13oP1neNhzBy1rCpoybItWYd05nXMyWZvPfxLkuWkT/ZZ6xlZ2usfVHrS+P6WmH/rXnJ3DFzfZb6BF3CZYqiW8EmR7Uia8v36TYgIanQAvK9J68Bg81wPxDuti+k9bTeBHo3tRD15leT/49qQaM//o3jC6E328IWhnojX4c9BeiCe0j/k/N/ATBH4Dk4mm3yAAAAABJRU5ErkJggg==', 9, 'Y', '1', 1, '系统管理', NULL, NULL, 1), (28, NULL, 27, '区域设置', '/index/system/region', NULL, 1, 'Y', '1', 1, '区域设置', NULL, NULL, 0), (29, NULL, 27, '操作日志', '/index/system/operation', NULL, 2, 'Y', '1', 1, '操作日志', NULL, NULL, 0), (30, NULL, 27, '成员管理', '/index/system/leaguer', NULL, 3, 'Y', '1', 1, '成员管理', NULL, NULL, 0), (31, NULL, 27, '角色权限', '/index/system/role', NULL, 4, 'Y', '1', 1, '角色权限', NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for newretail_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `newretail_sys_role`;
CREATE TABLE `newretail_sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色编码',
  `gmt_create` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `description` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '删除状态: 1-已删除,0-未删除',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态: 1-激活状态    0-锁定状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `IDX_ROLE_CODE`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色表';

-- ----------------------------
-- Records of newretail_sys_role
-- ----------------------------
BEGIN;
INSERT INTO `newretail_sys_role` VALUES (4, '系统管理员', 'admin', '2017-12-11 00:00:00', '2018-10-22 10:00:08', '胜多负少', 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for newretail_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `newretail_sys_user`;
CREATE TABLE `newretail_sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '登录账号',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '名称',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码盐',
  `phone_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电话号码',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电子邮箱',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态: 1-启用，0-禁用',
  `portrait` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '头像',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '用户类型：1-运营，2-商家，3-供应商',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '删除状态：1-已删除，0-未删除',
  `gmt_create` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `last_login_time` timestamp(0) NULL DEFAULT NULL COMMENT '最后一次登录时间',
  `last_login_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '上次一次登录ip',
  `login_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '本次登陆ip',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 228 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户信息表';

-- ----------------------------
-- Records of newretail_sys_user
-- ----------------------------
BEGIN;
INSERT INTO `newretail_sys_user` VALUES (127, 'admin', '系统管理员', '$2a$10$9EOb9PVYFLXzljSdf6YQaet0mvH2loOodKvvXVynGyq3yj2G9nCoC', '311', '13870931273', '350006811@qq.com', 1, 'http://192.168.10.22/group1/M00/00/05/wKgKFlvStUqAFun7AAAC0GrG37w435.jpg', NULL, 0, '2017-12-11 06:30:47', '2018-10-26 14:33:47', '2018-11-03 16:25:56', '192.168.10.66', '192.168.10.66', 's');
COMMIT;

-- ----------------------------
-- Table structure for newretail_user_role
-- ----------------------------
DROP TABLE IF EXISTS `newretail_user_role`;
CREATE TABLE `newretail_user_role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sys_user_id` bigint(20) NOT NULL COMMENT '用户id',
  `sys_role_id` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户与角色关联表';

-- ----------------------------
-- Records of newretail_user_role
-- ----------------------------
BEGIN;
INSERT INTO `newretail_user_role` VALUES (56, 127, 4);
COMMIT;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL,
  `refresh_token_validity` int(11) NULL DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` VALUES ('webApp', NULL, '$2a$10$UUUwnzpRTXdRtY61sGK.3eCRqP4fagTIj0Xr5PcB1joOITHyTMuqi', 'webApp', 'password,authorization_code,refresh_token,client_credentials', NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
