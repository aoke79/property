//messager
(function(jQuery) {
	
	//弹出层大小设置
	this.layer = {
		'width' : 250,
		'height' : 150
	};
	//弹出层标题
	this.title = '即时消息';
	//弹出层显示时间
	this.time = 5000;
	//弹出层表示方式及弹出速度
	this.anims = {
		'type' : 'slide',
		'speed' : 1000
	};

	//初始化事件
	this.inits = function(title, text) {
		if ($("#message").is("div")) {
			return;
		}
		$(document.body).prepend(
						'<div id="message" style="border:#b9c9ef 1px solid;z-index:100;width:' + this.layer.width
								+ 'px;height:' + this.layer.height
								+ 'px;position:absolute; display:none;background:#cfdef4; bottom:36px; right:7px; overflow:hidden;"><div style="border:1px solid #fff;border-bottom:none;width:100%;height:25px;font-size:12px;overflow:hidden;color:#1f336b;"><span id="message_close" style="float:right;padding:5px 0 5px 0;width:16px;line-height:auto;color:red;font-size:12px;font-weight:bold;text-align:center;cursor:pointer;overflow:hidden;">×</span><div style="padding:5px 0 5px 5px;width:100px;line-height:18px;text-align:left;overflow:hidden;">'
								+ title
								+ '</div><div style="clear:both;"></div></div> <div style="padding-bottom:5px;border:1px solid #fff;border-top:none;width:100%;height:auto;font-size:12px;"><div id="message_content" style="margin:0 5px 0 5px;border:#b9c9ef 1px solid;padding:10px 0 10px 5px;font-size:12px;width:'
								+ (this.layer.width - 17)
								+ 'px;height:'
								+ (this.layer.height - 50)
								+ 'px;color:#1f336b;text-align:left;overflow:hidden;">'
								+ text + '</div></div></div>');
	};

	//显示弹出层的事件
	this.show = function(title, text, time) {
		if ($("#message").is("div")) {
			return;
		}
		if (title == 0 || !title)
			title = this.title;
		this.inits(title, text);
		if (time)
			this.time = time;
		switch (this.anims.type) {
		case 'slide':
			$("#message").slideDown(this.anims.speed);
			break;
		case 'fade':
			$("#message").fadeIn(this.anims.speed);
			break;
		case 'show':
			$("#message").show(this.anims.speed);
			break;
		default:
			$("#message").slideDown(this.anims.speed);
			break;
		}
		$("#message_close").click(function() {
			setTimeout('this.close()', 1);
		});
		this.rmmessage(this.time);
	};

	//更改弹出层大小的事件
	this.lays = function(width, height) {
		if ($("#message").is("div")) {
			return;
		}
		if (width != 0 && width)
			this.layer.width = width;
		if (height != 0 && height)
			this.layer.height = height;
	}

	//更改弹出层的动画事件
	this.anim = function(type, speed) {
		if ($("#message").is("div")) {
			return;
		}
		if (type != 0 && type)
			this.anims.type = type;
		if (speed != 0 && speed) {
			switch (speed) {
			case 'slow':
				this.anims.speed = 1000;
				break;
			case 'fast':
				this.anims.speed = 200;
				break;
			case 'normal':
				this.anims.speed = 600;
				break;
			default:
				this.anims.speed = speed;
			}
		}
	}
	this.rmmessage = function(time) {
		//setTimeout('this.close()', time);
	};

	//关闭弹出层时的事件
	this.close = function() {
		switch (this.anims.type) {
		case 'slide':
			$("#message").slideUp(this.anims.speed);
			break;
		case 'fade':
			$("#message").fadeOut(this.anims.speed);
			break;
		case 'show':
			$("#message").hide(this.anims.speed);
			break;
		default:
			$("#message").slideUp(this.anims.speed);
			break;
		}
		;
		setTimeout('$("#message").remove();', this.anims.speed);
		this.original();
	};

	//默认初始化的弹出层事件
	this.original = function() {
		this.layer = {
			'width' : 200,
			'height' : 100
		};
		this.title = '即时消息';
		this.time = 4000;
		this.anims = {
			'type' : 'slide',
			'speed' : 600
		};
	};

	jQuery.messager = this;
	return jQuery;
})(jQuery);
