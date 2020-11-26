
-- test.goods definition

CREATE TABLE `goods` (
  `goods_id` int NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `goods_name` varchar(30) NOT NULL COMMENT '商品名称',
  `goods_title` varchar(100) NOT NULL DEFAULT '' COMMENT '商品标题',
  `goods_no` int NOT NULL COMMENT '商品编号',
  `goods_category` int NOT NULL DEFAULT '0' COMMENT '商品分类',
  `brand` varchar(30) NOT NULL DEFAULT '' COMMENT '品牌',
  `price` decimal(10,2) NOT NULL COMMENT '原价',
  `current_price` decimal(10,2) NOT NULL COMMENT '现价',
  `store_number` int NOT NULL DEFAULT '0' COMMENT '库存数量',
  `details` varchar(500) NOT NULL COMMENT '商品介绍详情',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品表';


-- test.orders definition

CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `order_no` int NOT NULL COMMENT '订单号',
  `user_id` int NOT NULL COMMENT '用户ID',
  `goods_id` int NOT NULL COMMENT '商品ID',
  `number` int NOT NULL DEFAULT '1' COMMENT '数量',
  `sums` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '金额',
  `create_time` datetime NOT NULL COMMENT '下单时间',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '订单状态',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表';


-- test.users definition

CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(20) NOT NULL COMMENT '用户名登录名',
  `nickname` varchar(20) DEFAULT '' COMMENT '昵称',
  `head_url` varchar(100) DEFAULT '' COMMENT '头像url',
  `gender` tinyint NOT NULL DEFAULT '0' COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `phone` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(50) NOT NULL DEFAULT '' COMMENT '邮箱',
  `password` varchar(16) NOT NULL COMMENT '登录密码',
  `pay_password` varchar(16) NOT NULL COMMENT '支付密码',
  `names` varchar(6) NOT NULL DEFAULT '' COMMENT '姓名',
  `ids` varchar(18) NOT NULL DEFAULT '' COMMENT '身份证',
  `create_time` datetime NOT NULL COMMENT '注册时间',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

