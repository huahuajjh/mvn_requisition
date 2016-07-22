function toTicketNumber(name){
	switch (name) {
	case "LOSSOFREPORT":
		return 1;
	case "EXCHANGEED":
		return 2;
	case "MENDED":
		return 3;
	case "USED":
		return 4;
	case "RECEIVED":
		return 5;
	case "CASHED":
		return 6;
	case "NORMAL":
		return 7;
	default:
		return 0;
	}
}
function toTicketStr(name){
	switch (name) {
	case "LOSSOFREPORT":
		return "挂失";
	case "EXCHANGEED":
		return "已换券";
	case "MENDED":
		return "已补券";
	case "USED":
		return "已兑换";
	case "RECEIVED":
		return "已领取";
	case "CASHED":
		return "已兑现";
	case "NORMAL":
		return "正常";
	default:
		return "";
	}
}