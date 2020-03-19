#! /usr/bin/python
# -*- coding:UTF-8 -*-

import os
import urllib
import urllib2
import json

#dir = '/home/cyk/pythonws/f'
dir = '/data/gmprd-makeseal-api/logs/nas/pdftest'
fileList = os.listdir(dir)
pdfFnList = []

for i in fileList:
    if os.path.splitext(i)[1] == '.pdf':
        pdfFnList.append(i)


test_data = {'apiKey':'test'}
td_url_encode = urllib.urlencode(test_data)

reqUrl = "http://127.0.0.1:10090/makesealapi/api/login/getToken"
req = urllib2.Request(url = reqUrl, data=td_url_encode)

res_data = urllib2.urlopen(req)
res = res_data.read()
print res

rtn = json.loads(res)
if 0 != rtn['errcode']:
    print "get token failure!"
    exit(0)

token =  rtn['token']
print token

finFnList = [] 
for i in pdfFnList:

    test_data = {'token':token, 'documentId': i}
    td2_encode = urllib.urlencode(test_data)
    reqUrl2 = "http://127.0.0.1:10090/makesealapi/api/gdata/pdf/uploadToObs?"+td2_encode

    req2 = urllib2.Request(url=reqUrl2)
    resdata2 = urllib2.urlopen(req2)
    res2 = resdata2.read()
    print res2
    rtn = json.loads(res2)
    if 0 == rtn['errcode'] :
        finFnList.append(i)
    else:
        print rtn['errmsg']


if len(finFnList) > 0 :
    print "record finish file list"
else:
    print "finish file list is empty"

f = open('bakFnList.txt', 'w')
print "start to move finish file"
for i in finFnList:
    if(os.path.exists(dir +"/" + i)):
        f.write(i)
        f.write('\n')
        os.remove(dir + "/" + i);
f.close

print "do finish!"
