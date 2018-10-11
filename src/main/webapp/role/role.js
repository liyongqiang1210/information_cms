$(function() {

	productsearch();
});
var limit = 10; // 页面数据条数
var offset = 1; // 当前页数

function productsearch() {

	layui
			.use(
					[ 'table', 'laypage', 'form' ],
					function() {
						var table = layui.table, laypage = layui.laypage, form = layui.form;

						// 第一个实例
						table
								.render({
									elem : '#role_table',
									height : 600,
									url : 'http://localhost:8080/Information_cms/role/getAll.do?offset='
											+ offset
											+ '&limit='
											+ limit
											+ '&rolename=', // 数据接口
									page : false, // 开启分页
									limit : 10,
									cols : [ [ // 表头
											{
												type : 'checkbox',
												fixed : 'left'
											},
											{
												field : 'id',
												title : 'ID',
												width : 80,
												sort : true,
												fixed : 'left'
											},
											{
												field : 'roleName',
												title : '角色名',
												width : 150
											},
											{
												field : 'roleDesc',
												title : '角色描述',
												width : 500
											},
											{
												field : 'available',
												title : '是否可用',
												width : 150,
												align : 'center',
												templet : function(d) {
													if (d.available == 1) {
														return '<input type="checkbox" id="'
																+ d.id
																+ '" lay-skin="switch" lay-filter="available" lay-text="开启|关闭" checked>';
													} else {
														return '<input type="checkbox" id="'
																+ d.id
																+ '" lay-skin="switch" lay-filter="available" lay-text="开启|关闭">';
													}
												}
											}, {
												field : 'operate',
												title : '操作',
												align : 'center',
												toolbar : '#operate_toolBar'
											} ] ],
									done : function(res, curr, count) {
										laypage.render({
											elem : 'laypage',
											count : count,
											curr : offset,
											limit : limit,
											layout : [ 'prev', 'page', 'next',
													'skip', 'count', 'limit' ],
											jump : function(obj, first) {
												if (!first) {
													offset = obj.curr;
													limit = obj.limit;
													productsearch();
												}
											}
										})
									}

								});

						// 监听开关按钮
						form.on('switch(available)', function(data) {
							var status = data.elem.checked; // 得到开关的状态
							console.log(status);
						});

						// // 表格的重载
						// table
						// .reload(
						// '#role_table_id',
						// {
						// height : 600,
						// url :
						// 'http://localhost:8080/Information_cms/role/getAll.do?offset=1&limit=30&rolename=',
						// // 数据接口
						// page : true, // 开启分页
						// cols : [ [ // 表头
						// {
						// type : 'checkbox',
						// fixed : 'left'
						// },
						// {
						// field : 'id',
						// title : 'ID',
						// width : 80,
						// sort : true,
						// fixed : 'left'
						// },
						// {
						// field : 'roleName',
						// title : '角色名',
						// width : 150
						// },
						// {
						// field : 'roleDesc',
						// title : '角色描述',
						// width : 500
						// },
						// {
						// field : 'available',
						// title : '是否可用',
						// width : 150,
						// align : 'center',
						// templet : function(d) {
						// if (d.available == 1) {
						// return '<input type="checkbox" id="'
						// + d.id
						// + '" lay-skin="switch" lay-filter="available"
						// lay-text="开启|关闭" checked>';
						// } else {
						// return '<input type="checkbox" id="'
						// + d.id
						// + '" lay-skin="switch" lay-filter="available"
						// lay-text="开启|关闭">';
						// }
						// }
						// },
						// {
						// field : 'operate',
						// title : '操作',
						// align : 'center',
						// toolbar : '#operate_toolBar'
						// } ] ]
						// });

					});
}
