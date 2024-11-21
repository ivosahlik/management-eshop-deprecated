$(document).ready(function() {

	$('.delete-product').on('click', function (){
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'remove';
	    /*]]>*/

		var id=$(this).attr('id');

		bootbox.confirm({
			message: "Are you sure to remove this product? This product can use some client.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.post(path, {'id':id}, function(res) {
						location.reload();
					});
				}
			}
		});
	});

	$('.delete-video').on('click', function (){
		/*<![CDATA[*/
		var path = /*[[@{/}]]*/'remove';
		/*]]>*/

		var id = $(this).attr('id').split("-")[1];

		bootbox.confirm({
			message: "Are you sure to remove this product? This product can use some client.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.post(path, {'id':id}, function(res) {
						location.reload();
					});
				}
			}
		});
	});

	$('.delete-transport').on('click', function (){
		/*<![CDATA[*/
		var path = /*[[@{/}]]*/'removeTransport';
		/*]]>*/

		var id=$(this).attr('id');

		bootbox.confirm({
			message: "Are you sure to remove this transport? This product can use some client.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.post(path, {'id':id}, function(res) {
						location.reload();
					});
				}
			}
		});
	});

	$('.delete-country').on('click', function (){
		/*<![CDATA[*/
		var path = /*[[@{/}]]*/'removeCountry';
		/*]]>*/

		var id=$(this).attr('id');

		bootbox.confirm({
			message: "Are you sure to remove this country? This product can use some client.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.post(path, {'id':id}, function(res) {
						location.reload();
					});
				}
			}
		});
	});

	$('.delete-zone').on('click', function (){
		/*<![CDATA[*/
		var path = /*[[@{/}]]*/'removeZone';
		/*]]>*/

		var id=$(this).attr('id');

		bootbox.confirm({
			message: "Are you sure to remove this zone? This product can use some client.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.post(path, {'id':id}, function(res) {
						location.reload();
					});
				}
			}
		});
	});

	$('.delete-file').on('click', function (){
		/*<![CDATA[*/
		var path = /*[[@{/}]]*/'removefile';
		/*]]>*/

		var id=$(this).attr('id');

		bootbox.confirm({
			message: "Are you sure to remove this file? This file can use some client.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.post(path, {'id':id}, function(res) {
						location.reload();
					});
				}
			}
		});
	});



//	$('.checkboxBook').click(function () {
//        var id = $(this).attr('id');
//        if(this.checked){
//            bookIdList.push(id);
//        }
//        else {
//            bookIdList.splice(bookIdList.indexOf(id), 1);
//        }
//    })

	$('#deleteSelected').click(function() {
		var idList= $('.checkboxData');
		var deleteIdList=[];
		for (var i = 0; i < idList.length; i++) {
			if(idList[i].checked===true) {
				deleteIdList.push(idList[i]['id'])
			}
		}

		console.log(deleteIdList);

		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'removeList';
	    /*]]>*/

	    bootbox.confirm({
			message: "Are you sure to remove all selected products? It can't be undone.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.ajax({
						type: 'POST',
						url: path,
						data: JSON.stringify(deleteIdList),
						contentType: "application/json",
						success: function(res) {
							console.log(res);
							location.reload()
							},
						error: function(res){
							console.log(res);
							location.reload();
							}
					});
				}
			}
		});
	});

	$("#selectAll").click(function() {
		if($(this).prop("checked")===true) {
			$(".checkboxData").prop("checked",true);
		} else if ($(this).prop("checked")===false) {
			$(".checkboxData").prop("checked",false);
		}
	})
});
