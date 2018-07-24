  /**
     * 传入NameValuePair的列表，按照url调用httpclient（closeable）进行http请求，强制utf-8编码
     */
    public Object postClient(String url, List<NameValuePair> nvlist) {
        String encoding = "UTF-8";
        // ----------
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 编码请求参数
        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(nvlist, encoding);
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(entity);
            CloseableHttpResponse httpResp = httpClient.execute(httpPost);
            if (200 == httpResp.getStatusLine().getStatusCode()) {
                HttpEntity httpEntity = httpResp.getEntity();
                if (null != httpEntity) {
                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(new InputStreamReader(httpEntity.getContent(), "UTF-8"), 10 * 1024);
                        StringBuffer strBuilder = new StringBuffer();
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            strBuilder.append(line);
                        }
                        return strBuilder.toString();//返回结果字符
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (reader != null) {
                            try {
                                reader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
