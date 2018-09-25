https://dev.mysql.com/doc/sakila/en/sakila-preface.html

sakila sample database 

sakila 样本数据库

### 1 Preface and Legal Notices

       This document describes the Sakila sample database—its history, installation, structure and usage.     

序言和法律声明

    本文档介绍sakila数据库的由来，安装，结构和使用。

###  Legal Notices

     Copyright © 2007, 2018, Oracle and/or its affiliates. All     rights reserved.   

2007，2018版所有版权归甲骨文公司及其附属公司。版权所有。

     This software and related documentation are provided under a license agreement containing restrictions on use and disclosure and are protected by intellectual property laws. Except as expressly permitted in your license agreement or allowed by law, you may not use, copy, reproduce, translate, broadcast, modify, license, transmit, distribute, exhibit, perform, publish, or display any part, in any form, or by any means. Reverse engineering, disassembly, or decompilation of this software, unless required by law for interoperability, is prohibited.   

  本软件及相关文档需在提供使用和披露限制许可后方可使用，并且相关内容受知识产品法保护。除非在许可协议中明确允许或法律允许，否则不得使用、复制、复版、翻译、广播、修改、许可、发送、分发、展示、表演、出版或以任何形式或以任何方式显示本软件和文档任何部分。 除非有互操作性的法律规定，否则禁止对本软件进行反向工程、反汇编或反编译。 

     The information contained herein is subject to change without notice and is not warranted to be error-free. If you find any errors, please report them to us in writing.   

  本文包含信息如有更改，恕不另行通知，也不保证没有错误。如您发现错误，请以书面形式通知我们。

     If this is software or related documentation that is delivered to the U.S. Government or anyone licensing it on behalf of the U.S. Government, then the following notice is applicable:   

     U.S. GOVERNMENT END USERS: Oracle programs, including any operating     system, integrated software, any programs installed on the hardware,     and/or documentation, delivered to U.S. Government end users are     "commercial computer software" pursuant to the applicable Federal     Acquisition Regulation and agency-specific supplemental regulations.     As such, use, duplication, disclosure, modification, and adaptation     of the programs, including any operating system, integrated     software, any programs installed on the hardware, and/or     documentation, shall be subject to license terms and license     restrictions applicable to the programs. No other rights are granted     to the U.S. Government.   

     This software or hardware is developed for general use in a variety     of information management applications. It is not developed or     intended for use in any inherently dangerous applications, including     applications that may create a risk of personal injury. If you use     this software or hardware in dangerous applications, then you shall     be responsible to take all appropriate fail-safe, backup,     redundancy, and other measures to ensure its safe use. Oracle     Corporation and its affiliates disclaim any liability for any     damages caused by use of this software or hardware in dangerous     applications.   

     Oracle and Java are registered trademarks of Oracle and/or its     affiliates. Other names may be trademarks of their respective     owners.   

     Intel and Intel Xeon are trademarks or registered trademarks of     Intel Corporation. All SPARC trademarks are used under license and     are trademarks or registered trademarks of SPARC International, Inc.     AMD, Opteron, the AMD logo, and the AMD Opteron logo are trademarks     or registered trademarks of Advanced Micro Devices. UNIX is a     registered trademark of The Open Group.   

     This software or hardware and documentation may provide access to or     information about content, products, and services from third     parties. Oracle Corporation and its affiliates are not responsible     for and expressly disclaim all warranties of any kind with respect     to third-party content, products, and services unless otherwise set     forth in an applicable agreement between you and Oracle. Oracle     Corporation and its affiliates will not be responsible for any loss,     costs, or damages incurred due to your access to or use of     third-party content, products, or services, except as set forth in     an applicable agreement between you and Oracle.   

     This documentation is NOT distributed under a GPL license. Use of     this documentation is subject to the following terms:   

     You may create a printed copy of this documentation solely for your     own personal use. Conversion to other formats is allowed as long as     the actual content is not altered or edited in any way. You shall     not publish or distribute this documentation in any form or on any     media, except if you distribute the documentation in a manner     similar to how Oracle disseminates it (that is, electronically for     download on a Web site with the software) or on a CD-ROM or similar     medium, provided however that the documentation is disseminated     together with the software on the same medium. Any other use, such     as any dissemination of printed copies or use of this documentation,     in whole or in part, in another publication, requires the prior     written consent from an authorized representative of Oracle. Oracle     and/or its affiliates reserve any and all rights to this     documentation not expressly granted above.   

###  Access to Oracle Support

     Oracle customers that have purchased support have access to     electronic support through My Oracle Support. For information, visit        <http://www.oracle.com/pls/topic/lookup?ctx=acc&id=info>     or visit     <http://www.oracle.com/pls/topic/lookup?ctx=acc&id=trs>     if you are hearing impaired.   