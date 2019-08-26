#include <jni.h>
#include <string>
#include "test.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_dream_mvpdemo_protocol_JniProtocol_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
   // std::string hello = "Hello from C++";
    return env->NewStringUTF(hf().c_str());
}