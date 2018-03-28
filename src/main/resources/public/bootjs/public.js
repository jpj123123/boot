var dateFormat=({
    year_format : "yyyy",
    month_format : "yyyy-MM-dd",
    date_format : "yyyy-MM-dd",
    min_format : "yyyy-MM-dd HH:mm",
    datetime_format : "yyyy-MM-dd HH:mm:ss",

    getDate : function(dateStr, format){

        if(format == undefined || format == null || dateStr ==undefined || dateStr == null)
            return null;

        var date = new Date(0);
        if(format.indexOf("yyyy")>=0){
            date.setFullYear(dateStr.substr(format.indexOf("yyyy"),4));
        }else{
            date.setFullYear("1970");
        }
        if(format.indexOf("MM")>=0){
            date.setMonth(new Number(dateStr.substr(format.indexOf("MM"),2))-1);
        }else{
            date.setMonth(0);
        }
        console.log(date.getFullYear())
        if(format.indexOf("dd")>=0){
            date.setDate(dateStr.substr(format.indexOf("dd"),2));
        }else{
            date.setDate(1);
        }
        if(format.indexOf("HH")>=0){
            date.setHours(dateStr.substr(format.indexOf("HH"),2));
        }else{
            date.setHours(0);
        }
        if(format.indexOf("mm")>=0){
            date.setMinutes(dateStr.substr(format.indexOf("mm"),2));
        }else{
            date.setMinutes(0);
        }
        if(format.indexOf("ss")>=0){
            date.setSeconds(dateStr.substr(format.indexOf("ss"),2));
        }else{
            date.setSeconds(0);
        }
        return date;
    },
    getTime : function(dateStr, format){
        var d = this.getDate(dateStr, format);
        if(d != null){
            return d.getTime();
        }
        return 0;
    }
})
var reload_page = function(id, data){
    var page = data.body;
    if(page){
        $('#'+id).pagination('refresh',{	// 改变选项，并刷新分页栏信息
            total: page.total,
            pageNumber: page.pageNum,
            pageSize: page.pageSize
        });
    }
}