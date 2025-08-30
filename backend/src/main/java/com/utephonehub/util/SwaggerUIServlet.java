package com.utephonehub.util;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Swagger UI Servlet - Hiển thị API Documentation
 */
public class SwaggerUIServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null || pathInfo.equals("/") || pathInfo.equals("/index.html")) {
            // Hiển thị Swagger UI
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            
            String swaggerHtml = generateSwaggerUI();
            response.getWriter().write(swaggerHtml);
        } else if (pathInfo.equals("/swagger.json")) {
            // Serve static swagger.json file
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            
            try {
                // Try to read static swagger.json file first
                java.io.InputStream inputStream = getServletContext().getResourceAsStream("/swagger.json");
                if (inputStream != null) {
                    String content = new String(inputStream.readAllBytes(), "UTF-8");
                    response.getWriter().write(content);
                    inputStream.close();
                } else {
                    // Fallback to generated JSON
                    String swaggerJson = generateSwaggerJSON();
                    response.getWriter().write(swaggerJson);
                }
            } catch (Exception e) {
                // Fallback to generated JSON
                String swaggerJson = generateSwaggerJSON();
                response.getWriter().write(swaggerJson);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    
    private String generateSwaggerUI() {
        return "<!DOCTYPE html>\n" +
               "<html>\n" +
               "<head>\n" +
               "    <title>UTE PhoneHub API Documentation</title>\n" +
               "    <link rel=\"stylesheet\" type=\"text/css\" href=\"https://unpkg.com/swagger-ui-dist@4.15.5/swagger-ui.css\" />\n" +
               "    <style>\n" +
               "        html { box-sizing: border-box; overflow: -moz-scrollbars-vertical; overflow-y: scroll; }\n" +
               "        *, *:before, *:after { box-sizing: inherit; }\n" +
               "        body { margin:0; background: #fafafa; }\n" +
               "    </style>\n" +
               "</head>\n" +
               "<body>\n" +
               "    <div id=\"swagger-ui\"></div>\n" +
               "    <script src=\"https://unpkg.com/swagger-ui-dist@4.15.5/swagger-ui-bundle.js\"></script>\n" +
               "    <script src=\"https://unpkg.com/swagger-ui-dist@4.15.5/swagger-ui-standalone-preset.js\"></script>\n" +
               "    <script>\n" +
               "        window.onload = function() {\n" +
               "            const ui = SwaggerUIBundle({\n" +
               "                url: '/ute-phonehub/api-docs/swagger.json?v=' + Date.now(),\n" +
               "                dom_id: '#swagger-ui',\n" +
               "                deepLinking: true,\n" +
               "                presets: [\n" +
               "                    SwaggerUIBundle.presets.apis,\n" +
               "                    SwaggerUIStandalonePreset\n" +
               "                ],\n" +
               "                plugins: [\n" +
               "                    SwaggerUIBundle.plugins.DownloadUrl\n" +
               "                ],\n" +
               "                layout: \"StandaloneLayout\",\n" +
               "                validatorUrl: null\n" +
               "            });\n" +
               "        };\n" +
               "    </script>\n" +
               "</body>\n" +
               "</html>";
    }
    
    private String generateSwaggerJSON() {
        return "{\n" +
               "  \"openapi\": \"3.0.3\",\n" +
               "  \"info\": {\n" +
               "    \"title\": \"UTE PhoneHub API\",\n" +
               "    \"version\": \"1.0.0\",\n" +
               "    \"description\": \"API Documentation for UTE PhoneHub E-commerce Platform\"\n" +
               "  },\n" +
               "  \"servers\": [\n" +
               "    {\n" +
               "      \"url\": \"/ute-phonehub\",\n" +
               "      \"description\": \"Local development server\"\n" +
               "    }\n" +
               "  ],\n" +
               "  \"paths\": {\n" +
               "    \"/\": {\n" +
               "      \"get\": {\n" +
               "        \"summary\": \"Get project information\",\n" +
               "        \"description\": \"Returns basic information about the UTE PhoneHub project\",\n" +
               "        \"responses\": {\n" +
               "          \"200\": {\n" +
               "            \"description\": \"Project information\",\n" +
               "            \"content\": {\n" +
               "              \"application/json\": {\n" +
               "                \"schema\": {\n" +
               "                  \"type\": \"object\",\n" +
               "                  \"properties\": {\n" +
               "                    \"project\": { \"type\": \"string\" },\n" +
               "                    \"version\": { \"type\": \"string\" },\n" +
               "                    \"description\": { \"type\": \"string\" },\n" +
               "                    \"endpoints\": { \"type\": \"object\" }\n" +
               "                  }\n" +
               "                }\n" +
               "              }\n" +
               "            }\n" +
               "          }\n" +
               "        }\n" +
               "      }\n" +
               "    },\n" +
               "    \"/api/auth\": {\n" +
               "      \"get\": {\n" +
               "        \"tags\": [\"Authentication\"],\n" +
               "        \"summary\": \"Get auth module info\",\n" +
               "        \"description\": \"Returns information about the authentication module\",\n" +
               "        \"responses\": {\n" +
               "          \"200\": {\n" +
               "            \"description\": \"Auth module information\",\n" +
               "            \"content\": {\n" +
               "              \"application/json\": {\n" +
               "                \"schema\": {\n" +
               "                  \"type\": \"object\",\n" +
               "                  \"properties\": {\n" +
               "                    \"module\": { \"type\": \"string\" },\n" +
               "                    \"description\": { \"type\": \"string\" },\n" +
               "                    \"version\": { \"type\": \"string\" },\n" +
               "                    \"endpoints\": { \"type\": \"object\" }\n" +
               "                  }\n" +
               "                }\n" +
               "              }\n" +
               "            }\n" +
               "          }\n" +
               "        }\n" +
               "      }\n" +
               "    },\n" +
               "    \"/api/products\": {\n" +
               "      \"get\": {\n" +
               "        \"tags\": [\"Products\"],\n" +
               "        \"summary\": \"Get product module info\",\n" +
               "        \"description\": \"Returns information about the product module\",\n" +
               "        \"responses\": {\n" +
               "          \"200\": {\n" +
               "            \"description\": \"Product module information\",\n" +
               "            \"content\": {\n" +
               "              \"application/json\": {\n" +
               "                \"schema\": {\n" +
               "                  \"type\": \"object\",\n" +
               "                  \"properties\": {\n" +
               "                    \"module\": { \"type\": \"string\" },\n" +
               "                    \"description\": { \"type\": \"string\" },\n" +
               "                    \"version\": { \"type\": \"string\" },\n" +
               "                    \"endpoints\": { \"type\": \"object\" }\n" +
               "                  }\n" +
               "                }\n" +
               "              }\n" +
               "            }\n" +
               "          }\n" +
               "        }\n" +
               "      }\n" +
               "    },\n" +
               "    \"/api/cart\": {\n" +
               "      \"get\": {\n" +
               "        \"tags\": [\"Shopping Cart\"],\n" +
               "        \"summary\": \"Get cart module info\",\n" +
               "        \"description\": \"Returns information about the cart module\",\n" +
               "        \"responses\": {\n" +
               "          \"200\": {\n" +
               "            \"description\": \"Cart module information\",\n" +
               "            \"content\": {\n" +
               "              \"application/json\": {\n" +
               "                \"schema\": {\n" +
               "                  \"type\": \"object\",\n" +
               "                  \"properties\": {\n" +
               "                    \"module\": { \"type\": \"string\" },\n" +
               "                    \"description\": { \"type\": \"string\" },\n" +
               "                    \"version\": { \"type\": \"string\" },\n" +
               "                    \"endpoints\": { \"type\": \"object\" }\n" +
               "                  }\n" +
               "                }\n" +
               "              }\n" +
               "            }\n" +
               "          }\n" +
               "        }\n" +
               "      }\n" +
               "    },\n" +
               "    \"/api/orders\": {\n" +
               "      \"get\": {\n" +
               "        \"tags\": [\"Orders\"],\n" +
               "        \"summary\": \"Get order module info\",\n" +
               "        \"description\": \"Returns information about the order module\",\n" +
               "        \"responses\": {\n" +
               "          \"200\": {\n" +
               "            \"description\": \"Order module information\",\n" +
               "            \"content\": {\n" +
               "              \"application/json\": {\n" +
               "                \"schema\": {\n" +
               "                  \"type\": \"object\",\n" +
               "                  \"properties\": {\n" +
               "                    \"module\": { \"type\": \"string\" },\n" +
               "                    \"description\": { \"type\": \"string\" },\n" +
               "                    \"version\": { \"type\": \"string\" },\n" +
               "                    \"endpoints\": { \"type\": \"object\" }\n" +
               "                  }\n" +
               "                }\n" +
               "              }\n" +
               "            }\n" +
               "          }\n" +
               "        }\n" +
               "      }\n" +
               "    },\n" +
               "    \"/api/reviews\": {\n" +
               "      \"get\": {\n" +
               "        \"tags\": [\"Reviews\"],\n" +
               "        \"summary\": \"Get review module info\",\n" +
               "        \"description\": \"Returns information about the review module\",\n" +
               "        \"responses\": {\n" +
               "          \"200\": {\n" +
               "            \"description\": \"Review module information\",\n" +
               "            \"content\": {\n" +
               "              \"application/json\": {\n" +
               "                \"schema\": {\n" +
               "                  \"type\": \"object\",\n" +
               "                  \"properties\": {\n" +
               "                    \"module\": { \"type\": \"string\" },\n" +
               "                    \"description\": { \"type\": \"string\" },\n" +
               "                    \"version\": { \"type\": \"string\" },\n" +
               "                    \"endpoints\": { \"type\": \"object\" }\n" +
               "                  }\n" +
               "                }\n" +
               "              }\n" +
               "            }\n" +
               "          }\n" +
               "        }\n" +
               "      }\n" +
               "    },\n" +
               "    \"/api/admin\": {\n" +
               "      \"get\": {\n" +
               "        \"tags\": [\"Admin\"],\n" +
               "        \"summary\": \"Get admin module info\",\n" +
               "        \"description\": \"Returns information about the admin module\",\n" +
               "        \"responses\": {\n" +
               "          \"200\": {\n" +
               "            \"description\": \"Admin module information\",\n" +
               "            \"content\": {\n" +
               "              \"application/json\": {\n" +
               "                \"schema\": {\n" +
               "                  \"type\": \"object\",\n" +
               "                  \"properties\": {\n" +
               "                    \"module\": { \"type\": \"string\" },\n" +
               "                    \"description\": { \"type\": \"string\" },\n" +
               "                    \"version\": { \"type\": \"string\" },\n" +
               "                    \"endpoints\": { \"type\": \"object\" }\n" +
               "                  }\n" +
               "                }\n" +
               "              }\n" +
               "            }\n" +
               "          }\n" +
               "        }\n" +
               "      }\n" +
               "    },\n" +
               "    \"/api/vouchers\": {\n" +
               "      \"get\": {\n" +
               "        \"tags\": [\"Vouchers\"],\n" +
               "        \"summary\": \"Get voucher module info\",\n" +
               "        \"description\": \"Returns information about the voucher module\",\n" +
               "        \"responses\": {\n" +
               "          \"200\": {\n" +
               "            \"description\": \"Voucher module information\",\n" +
               "            \"content\": {\n" +
               "              \"application/json\": {\n" +
               "                \"schema\": {\n" +
               "                  \"type\": \"object\",\n" +
               "                  \"properties\": {\n" +
               "                    \"module\": { \"type\": \"string\" },\n" +
               "                    \"description\": { \"type\": \"string\" },\n" +
               "                    \"version\": { \"type\": \"string\" },\n" +
               "                    \"endpoints\": { \"type\": \"object\" }\n" +
               "                  }\n" +
               "                }\n" +
               "              }\n" +
               "            }\n" +
               "          }\n" +
               "        }\n" +
               "      }\n" +
               "    }\n" +
               "  }\n" +
               "}";
    }
}
