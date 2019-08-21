#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_dream_mvpdemo_protocol_JniProtocol_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT void JNICALL
Java_com_dream_mvpdemo_protocol_JniProtocol_cppToJava(JNIEnv *env, jobject jobj) {
    jclass cls_First = env->GetObjectClass(jobj);
    if(cls_First == NULL){
        printf("cls_First == NULL");
        return;
    }

    jmethodID  mid = env->GetMethodID(cls_First, "operation", "()I");
//    if(mid == NULL){
//        printf("mid == NULL");
//        return;
//    }

    env->CallVoidMethod(cls_First,mid);

    //jthrowable exc = env->ExceptionOccurred();
   // if(exc){
        // 打印异常日志
        env->ExceptionDescribe();
        // 这行代码才是关键不让应用崩溃的代码，
        env->ExceptionClear();
        // 发生异常了要记得释放资源
        env->DeleteLocalRef(cls_First);
       // env->DeleteLocalRef(jid);
       // env->DeleteLocalRef(data);
   // }

}extern "C"
JNIEXPORT void JNICALL
Java_com_dream_mvpdemo_protocol_JniProtocol_cppToasda(JNIEnv *env, jobject instance) {

    // TODO

}


