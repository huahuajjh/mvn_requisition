package com.tq.requisition.presentation.actions.socialSecurityMansgement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.excel.util.ExcelFactory;
import com.excel.util.error.ConvertErrorException;
import com.excel.util.intefaces.IErrorCallback;
import com.excel.util.intefaces.IExcelInput;
import com.excel.util.model.ExcelRowError;
import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;
import com.tq.requisition.infrastructure.utils.ConfigFileUtil;
import com.tq.requisition.infrastructure.utils.Formater;
import com.tq.requisition.infrastructure.utils.Serialization;
import com.tq.requisition.infrastructure.utils.Formater.OperationResult;
import com.tq.requisition.presentation.Interceptor.LoginInterceptor;
import com.tq.requisition.presentation.dto.socialsecurityMgt.SsImportAndExportDto;
import com.tq.requisition.presentation.serviceContract.socialsecurityMgt.ISocialsecurityMgtServiceContract;

@SuppressWarnings("serial")
public class SolmUpFile extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 1024 * 4);
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> fileList = null;
		try {
			fileList = upload.parseRequest(request);
		} catch (FileUploadException e) {
			response.getWriter().write(e.getMessage());
		} catch (Exception e) {
			response.getWriter().write(e.getMessage());
		}
//		InputStream is = null;
//		for (FileItem item : fileList) {
//			if (!item.isFormField()) {
//				{
//					is = item.getInputStream();// 上传文件流
//				}
//			}
//		}
//		response.setHeader("Content-type", "text/html;charset=UTF-8");
//		ISocialsecurityMgtServiceContract ssService = ServiceLocator.instance()
//				.getService("ssService",
//						ISocialsecurityMgtServiceContract.class);
//		String userId = null;
//		for (Cookie cookie : request.getCookies()) {
//			if (cookie.getName().equals(LoginInterceptor.LOGIN)) {
//				userId = cookie.getValue();
//			}
//		}
//		InputStream fileInputStream = is;
//		IExcelInput excelInput = null;
//		try {
//			final List<ExcelRowError> errors = new ArrayList<ExcelRowError>();
//			int errorNum = Integer.parseInt(ConfigFileUtil
//					.readByKey("errorNum"));
//			excelInput = ExcelFactory.getExcelInput(fileInputStream, errorNum,
//					new IErrorCallback() {
//						@Override
//						public void callback(List<ExcelRowError> subErrors) {
//							errors.addAll(subErrors);
//						}
//					});
//			try {
//				List<SsImportAndExportDto> items = excelInput.getDatas(
//						SsImportAndExportDto.class, 2);
//				if (errors.size() > 0) {
//					String str = toForMaterJson(OperationResult.ERROR,
//							"上传错误，请按错误按钮查看详细错误", errors.toArray());
//					response.getWriter().write(str);
//					return;
//				} else {
//					String stateJsonString = "";
//					if (items.size() > 0) {
//						for (SsImportAndExportDto item : items) {
//							item.setUserId(UUID.fromString(userId));
//							item.setCreateDate(new Date());
//							item.setCreateUid(userId);
//						}
//						stateJsonString = ssService.importSS(items);
//					} else {
//						stateJsonString = toForMaterJson(
//								OperationResult.SUCCESS, "文件中没有数据");
//					}
//					response.getWriter().write(stateJsonString);
//				}
//			} catch (InstantiationException | NoSuchMethodException
//					| ConvertErrorException e) {
//				String str = toForMaterJson(OperationResult.ERROR,
//						"获取Excel列表错误");
//				response.getWriter().write(str);
//			}
//		} catch (IOException e) {
//			String str = toForMaterJson(OperationResult.ERROR, "请上传正确的Excel文件");
//			response.getWriter().write(str);
//		} finally {
//			if (excelInput != null) {
//				excelInput.close();
//			}
//			if (fileInputStream != null) {
//				fileInputStream.close();
//			}
//		}
	}

	private String toForMaterJson(OperationResult type, String msg, Object data) {
		return Serialization.toJson(Formater.obtain(msg, data, type));
	}

	protected String toForMaterJson(OperationResult type, String msg) {
		return Serialization.toJson(Formater.obtain(msg, null, type));
	}
}
