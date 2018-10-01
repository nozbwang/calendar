$(".J-delete-confirm").click(function(e){
	e.preventDefault();
	var link =$(this).attr("href");
	if (!link) {
		return;
	}
	swal({
		  title: '确认要删除么？',
		  type: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: '确认',
		  cancelButtonText: '取消'
		}).then(function () {
		  window.location.href = link;
		})
});