layui
		.use(
				[ 'table', 'laypage' ],
				function() {
					var table = layui.table, laypage = layui.laypage;

					// 第一个实例
					table
							.render({
								elem : '#role_table',
								height : 500,
								url : 'http://localhost:8080/Information_cms/role/getAllRole.do?limit=10&offset=1000' // 数据接口
								,
								page : true // 开启分页
								,
								cols : [ [ // 表头
								{
									type : 'checkbox',
									fixed : 'left'
								}, {
									field : 'id',
									title : 'ID',
									width : 80,
									sort : true,
									fixed : 'left'
								}, {
									field : 'roleName',
									title : '角色名',
									width : 180
								}, {
									field : 'roleDesc',
									title : '角色描述',
									width : 380
								}, {
									field : 'available',
									title : '是否可用',
									width : 180
								}, {
									field : 'operate',
									title : '操作',
									width : 500,
									align : 'center'
								} ] ]
							});
					// 执行一个laypage实例
					laypage.render({
						elem : 'pageDemo' // 注意，这里 是 ID，不用加 # 号
						,
						count : 50,
						skin : '#1E9FFF' // 自定义选中色值
						// ,skip: true //开启跳页
						,
						jump : function(obj, first) {
							if (!first) {
								layer.msg('第' + obj.curr + '页', {
									offset : 'b'
								});
							}
						}
					// 数据总数，从服务端得到
					});

				});