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

extern "C"
JNIEXPORT jintArray JNICALL
Java_com_dream_mvpdemo_protocol_JniProtocol_testArray(JNIEnv *env, jobject instance,
                                                      jintArray test_, jint length) {
   // jint *test = env->GetIntArrayElements(test_, NULL);

    // TODO

  //  env->ReleaseIntArrayElements(test_, test, 0);

         int nLength = env->GetArrayLength(test_);
         int *pArr = env->GetIntArrayElements(test_, 0);

        for(int i=0; i < nLength; i++){
                 *(pArr + i) += length;
        }
        env->ReleaseIntArrayElements(test_,pArr,0);
        return test_;
}