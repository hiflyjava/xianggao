$(function() {
    var settings = {
        url: ctx + "mobile/list",
        pageSize: 10,
        queryParams: function(params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                mobile: $(".user-table-form").find("input[name='mobile']").val().trim(),
                // userId: $(".user-table-form").find("input[name='userId']").val(),
                status: $(".user-table-form").find("select[name='status']").val()
            };
        },
        columns: [{
                checkbox: true
            }, {
                field: 'id',
                visible: false
            },

            {
                field: 'username',
                title: '用户名'
            },

            {
                field: 'mobile',
                title: '电话号码'
            },


            {
                field: 'type',
                title: '运营商类型',
                formatter: function(value, row, index) {
                    if (value == 'YD') return '移动';
                    else if (value == 'LT') return '联通';
                    else if (value == 'DX') return '电信';
                    else return '保密';
                }
            },


            {
            field: 'province',
            title: '省'
            },

            {
                field: 'city',
                title: '市'
            },




            {
                field: 'createDate',
                title: '创建时间'
            }, {
                field: 'status',
                title: '发送状态',
                formatter: function(value, row, index) {
                    if (value == '1') return '<span class="badge badge-success">成功</span>';
                    if (value == '2') return '<span class="badge badge-warning">未知</span>';
                    if (value == '3') return '<span class="badge badge-warning">失败</span>';
                }
            }

        ]
    }
    $MB.initTable('userTable', settings);
});

function search() {
    $MB.refreshTable('userTable');
}

function refresh() {
    $(".user-table-form")[0].reset();
    $MB.refreshTable('userTable');
}

function deleteUsers() {
    var selected = $("#userTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    var contain = false;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的用户！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].userId;
        if (i != (selected_length - 1)) ids += ",";
        if (userName == selected[i].username) contain = true;
    }
    if (contain) {
        $MB.n_warning('勾选用户中包含当前登录用户，无法删除！');
        return;
    }

    $MB.confirm({
        text: "确定删除选中用户？",
        confirmButtonText: "确定删除"
    }, function() {
        $.post(ctx + 'user/delete', { "ids": ids }, function(r) {
            if (r.code == 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportUserExcel(){
	$.post(ctx+"user/excel",$(".user-table-form").serialize(),function(r){
		if (r.code == 0) {
			window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
		} else {
			$MB.n_warning(r.msg);
		}
	});
}




function exportUserCsv(){
	$.post(ctx+"user/csv",$(".user-table-form").serialize(),function(r){
		if (r.code == 0) {
			window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
		} else {
			$MB.n_warning(r.msg);
		}
	});
}