LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := dial
LOCAL_SRC_FILES := dial.c

include $(BUILD_SHARED_LIBRARY)
