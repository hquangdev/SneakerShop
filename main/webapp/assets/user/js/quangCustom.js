
document.addEventListener("DOMContentLoaded", function() {
	var priceLink = document.getElementById("priceLink");
	if (priceLink) {
		var price = parseFloat(priceLink.getAttribute("data-price"));
		if (!isNaN(price)) {
			var roundedPrice = Math.round(price);
			priceLink.innerHTML = roundedPrice.toLocaleString('vi-VN') + ' đ';
		} else {
			console.error("Giá không hợp lệ.");
		}
	} else {
		console.error("Không tìm thấy phần tử liên kết giá.");
	}
});
