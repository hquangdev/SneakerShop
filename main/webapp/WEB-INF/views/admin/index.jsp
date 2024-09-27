<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="page-content">

	<div class="ace-settings-container" id="ace-settings-container">
		<div class="btn btn-app btn-xs btn-warning ace-settings-btn"
			id="ace-settings-btn">
			<i class="ace-icon fa fa-cog bigger-130"></i>
		</div>

		<div class="page-header">
			<h1>
				Dashboard <small> <i
					class="ace-icon fa fa-angle-double-right"></i> Overview &amp; Stats
				</small>
			</h1>
		</div>
		<!-- /.page-header -->

		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="alert alert-block alert-success">
					<button type="button" class="close" data-dismiss="alert">
						<i class="ace-icon fa fa-times"></i>
					</button>

					<i class="ace-icon fa fa-check green"></i> Xin chào <strong
						class="green"> Ace <small>(v1.3.3)</small>
					</strong>, Chào mừng bạn đến với trang quản lí hệ thống <a
						href="https://github.com/bopoda/ace"></a>.
				</div>

				<div class="row">
					<div class="space-6"></div>

					<!-- Bắt đầu chỉnh sửa -->
					<div class="col-sm-7 infobox-container">

						<!-- Tổng giày trong kho -->
						<div class="infobox infobox-green">
							<div class="infobox-icon">
								<i class="ace-icon fa fa-cube"></i>
							</div>
							<div class="infobox-data">
								<a href="<c:url value='manager-product'/>"> <span
									class="infobox-data-number">${totalShoes}</span></a>
								<div class="infobox-content">Tổng giày trong kho</div>
							</div>
						</div>

						<!-- Tổng giày đã bán -->
						<div class="infobox infobox-blue">
							<div class="infobox-icon">
								<i class="ace-icon fa fa-shopping-cart"></i>
							</div>

							<div class="infobox-data">
								<span class="infobox-data-number">${totalShoesSold }</span>
								<!-- Dữ liệu mẫu -->
								<div class="infobox-content">Tổng giày đã bán</div>
							</div>
						</div>

						<!-- Tổng hóa đơn -->
						<div class="infobox infobox-pink">
							<div class="infobox-icon">
								<i class="ace-icon fa fa-credit-card"></i>
							</div>

							<div class="infobox-data">
								<span class="infobox-data-number">${totalBill }</span>
								<!-- Dữ liệu mẫu -->
								<div class="infobox-content">Tổng hóa đơn</div>
							</div>
						</div>


						<!-- Doanh thu -->
						<div class="infobox infobox-yellow">
							<div class="infobox-icon">
								<i class="ace-icon fa fa-dollar"></i>
							</div>
							<div class="infobox-data">
								<span class="infobox-data-number"> <fmt:formatNumber>${totalrevenue } </fmt:formatNumber>₫
								</span>
								<!-- Dữ liệu mẫu -->
								<div class="infobox-content">Tổng doanh thu</div>
							</div>
						</div>


						<!-- Khách hàng mới và quay lại -->
						<div class="infobox infobox-blue">
							<div class="infobox-icon">
								<i class="ace-icon fa fa-users"></i>
							</div>
							<div class="infobox-data">
								<span class="infobox-data-number">${totalNewClient }</span>
								<!-- Dữ liệu mẫu -->
								<div class="infobox-content">Khách hàng mới</div>

							</div>
						</div>

						<!-- Doanh thu -->
						<div class="infobox infobox-yellow">
							<div class="infobox-icon">
								<i class="ace-icon fa fa-dollar"></i>
							</div>
							<div class="infobox-data">
								<span class="infobox-data-number">${totalRepeatCustomerCount }</span>
								<!-- Dữ liệu mẫu -->
								<div class="infobox-content">Lượng khách quay lại</div>
							</div>
						</div>



						<!-- Bộ lọc theo ngày tháng năm -->
						<div class="infobox infobox-orange">
							<div class="infobox-icon">
								<i class="ace-icon fa fa-calendar"></i>
							</div>
							<div class="infobox-data">
								<div class="infobox-content">
									<form action="#" method="POST" class="filter-form">
										<div class="form-group">
											<label for="startDate">Ngày bắt đầu:</label> <input
												type="date" id="startDate" name="startDate"
												class="form-control">
										</div>
										<div class="form-group">
											<label for="endDate">Ngày kết thúc:</label> <input
												type="date" id="endDate" name="endDate" class="form-control">
										</div>
										<button type="submit" class="btn btn-primary">Lọc</button>
									</form>
								</div>
							</div>
						</div>
					</div>
					<!-- Kết thúc chỉnh sửa -->

					<div class="vspace-12-sm"></div>

					<div class="col-sm-5">
						<div class="widget-box">
							<div class="widget-header widget-header-flat widget-header-small">
								<h5 class="widget-title">
									<i class="ace-icon fa fa-signal"></i> Traffic Sources
								</h5>

								<div class="widget-toolbar no-border">
									<div class="inline dropdown-hover">
										<button class="btn btn-minier btn-primary">
											This Week <i
												class="ace-icon fa fa-angle-down icon-on-right bigger-110"></i>
										</button>

										<ul
											class="dropdown-menu dropdown-menu-right dropdown-125 dropdown-lighter dropdown-close dropdown-caret">
											<li class="active"><a href="#" class="blue"> <i
													class="ace-icon fa fa-caret-right bigger-110">&nbsp;</i>
													This Week
											</a></li>

											<li><a href="#"> <i
													class="ace-icon fa fa-caret-right bigger-110 invisible">&nbsp;</i>
													Last Week
											</a></li>

											<li><a href="#"> <i
													class="ace-icon fa fa-caret-right bigger-110 invisible">&nbsp;</i>
													This Month
											</a></li>

											<li><a href="#"> <i
													class="ace-icon fa fa-caret-right bigger-110 invisible">&nbsp;</i>
													Last Month
											</a></li>
										</ul>
									</div>
								</div>
							</div>

							<div class="widget-body">
								<div class="widget-main">
									<div id="piechart-placeholder"></div>

									<div class="hr hr8 hr-double"></div>

									<div class="clearfix">
										<div class="grid3">
											<span class="grey"> <i
												class="ace-icon fa fa-facebook-square fa-2x blue"></i>
												&nbsp; likes
											</span>
											<h4 class="bigger pull-right">1,255</h4>
										</div>

										<div class="grid3">
											<span class="grey"> <i
												class="ace-icon fa fa-twitter-square fa-2x purple"></i>
												&nbsp; tweets
											</span>
											<h4 class="bigger pull-right">941</h4>
										</div>

										<div class="grid3">
											<span class="grey"> <i
												class="ace-icon fa fa-pinterest-square fa-2x red"></i>
												&nbsp; pins
											</span>
											<h4 class="bigger pull-right">1,050</h4>
										</div>
									</div>
								</div>
								<!-- /.widget-main -->
							</div>
							<!-- /.widget-body -->
						</div>
						<!-- /.widget-box -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->

				<div class="hr hr32 hr-dotted"></div>

				<div class="hr hr32 hr-dotted"></div>
				<div class="row">
					<div class="col-xs-12">
						<div class="widget-box">
							<div class="widget-header widget-header-flat widget-header-small">
								<h5 class="widget-title">
									<i class="ace-icon fa fa-users"></i> Bảng thống kê mua hàng của
									khách hàng
								</h5>
							</div>

							<div class="widget-body">
								<div class="widget-main">
									<table class="table table-striped table-bordered">
										<thead>
											<tr>
												<th>ID</th>
												<th>Họ và tên</th>
												<th>Tổng hóa đơn</th>
												<th>Tổng tiền</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="stat" items="${customerStats}">
												<tr>
													<td>${stat.customerId}</td>
													<td>${stat.customerName}</td>
													<td>${stat.totalOrders}</td>
													<td><fmt:formatNumber>${stat.totalAmount}</fmt:formatNumber></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->
	</div>
</div>
<!-- /.page-content -->