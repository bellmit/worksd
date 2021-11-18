// JavaScript Document
      /*
     	时间按钮
	   */
	   var dateRange1 = new pickerDateRange('date1', {
			isTodayValid : true,
			needCompare : false,
			defaultText : ' Để',
			autoSubmit : true,
			stopToday:true,
			theme : 'ta',
			success : function(obj) {
			$("#starTime").val(obj.startDate);
			$("#endTime").val(obj.endDate);
		  }
		});


		var dateRange2 = new pickerDateRange('date2', {
			isTodayValid : true,
			needCompare : false,
			defaultText : ' Để',
			autoSubmit : true,
			stopToday:true,
			theme : 'ta',
			success : function(obj) {
			console.log("-----date2---");
			console.log(obj);
			$("#starTime2").val(obj.startDate);
			$("#endTime2").val(obj.endDate);
		  }
		});
	 