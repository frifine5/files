package ta;

import java.util.HashMap;
import java.util.Map;

@TestA
public class TestAnnotation {
	@TestA //ʹ�������Աע��
    private Integer age;
    
    @TestA //ʹ���˹��췽��ע��
    public TestAnnotation(){
        
    }
    @TestA //ʹ�����෽��ע��
    public void a(){
        @TestA //ʹ���˾ֲ�����ע��
        Map m = new HashMap(0);
    }
    
    public void b(@TestA Integer a){ //ʹ���˷�������ע��
        
    }
	
}
