<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>Dashboard - Ace Admin</title>

    <meta name="description" content="overview &amp; stats" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="<c:url value='/assets/admin/css/bootstrap.min.css'/>" />
    <link rel="stylesheet" href="<c:url value='/assets/admin/font-awesome/4.2.0/css/font-awesome.min.css'/>" />

    
    <!-- text fonts -->
    <link rel="stylesheet" href="<c:url value='/assets/admin/fonts/fonts.googleapis.com.css'/>" />
    
    <link rel="stylesheet" href="<c:url value='/assets/admin/css/ace.min.css'/>" class="ace-main-stylesheet" id="main-ace-style" />
    
    <script src="<c:url value='/assets/admin/js/jquery.2.1.1.min.js'/>"></script>
    <script type="text/javascript">
        window.jQuery || document.write("<script src='<c:url value='/assets/js/jquery.min.js'/>'>"+"<"+"/script>");
    </script>
    <script type="text/javascript">
        if('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
    </script>
    <script src="<c:url value='/assets/admin/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/assets/admin/js/jquery-ui.custom.min.js'/>"></script>
    <script src="<c:url value='/assets/admin/js/jquery.ui.touch-punch.min.js'/>"></script>
    <script src="<c:url value='/assets/admin/js/jquery.easypiechart.min.js'/>"></script>
    <script src="<c:url value='/assets/admin/js/jquery.sparkline.min.js'/>"></script>
    <script src="<c:url value='/assets/admin/js/jquery.flot.min.js'/>"></script>
    <script src="<c:url value='/assets/admin/js/jquery.flot.pie.min.js'/>"></script>
    <script src="<c:url value='/assets/admin/js/jquery.flot.resize.min.js'/>"></script>
    
    <!-- ace scripts -->
    <script src="<c:url value='/assets/admin/js/ace-elements.min.js'/>"></script>
    <script src="<c:url value='/assets/admin/js/ace.min.js'/>"></script>

    <link rel="stylesheet" href="<c:url value='/assets/admin/cssSneaker/style.css'/>" />
</head>

<body class="no-skin">
    <div id="navbar" class="navbar navbar-default" style="height: 30px">
        <script type="text/javascript">
            try{ace.settings.check('navbar' , 'fixed')}catch(e){}
        </script>

        <div class="navbar-container" id="navbar-container">
          

            <%@include file="/WEB-INF/views/layouts/Admin/header.jsp" %>
        </div><!-- /.navbar-container -->
    </div>

    <div class="main-container" id="main-container">
        <script type="text/javascript">
            try{ace.settings.check('main-container' , 'fixed')}catch(e){}
        </script>

        <%@include file="/WEB-INF/views/layouts/Admin/sidebar.jsp" %>

        <div class="main-content">
            <div class="main-content-inner">
                <div class="breadcrumbs" id="breadcrumbs">
                    <script type="text/javascript">
                        try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                    </script>

                    <ul class="breadcrumb">
                        <li>
                            <a href="<c:url value='/home'/>">Trang chủ</a>
                        </li>
                        <li class="active">Dashboard</li>
                    </ul><!-- /.breadcrumb -->

                    <div class="nav-search searchName" id="nav-search">
					    <form class="form-search" action="search" method="post">
					        <span class="input-icon">
					            <input type="text" placeholder="Search..." class="nav-search-input" 
					            name="keyname" id="nav-search-input" autocomplete="off" />
					            <i class="ace-icon fa fa-search nav-search-icon"></i>
					        </span>
					    </form>
					</div>
                </div>

                <decorator:body/>
            </div>
        </div><!-- /.main-content -->

        <%@include file="/WEB-INF/views/layouts/Admin/footer.jsp" %>

        <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
            <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
        </a>
    </div><!-- /.main-container -->

    <!-- Inline scripts related to this page -->
    <script type="text/javascript">
        jQuery(function($) {
            // Your custom scripts here
        });
    </script>
</body>
</html>
