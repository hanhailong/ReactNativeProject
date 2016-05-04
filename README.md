# ReactNativeProject
因项目需要，最近两天研究RN，这里介绍已经存在的一个项目是如何移植RN的

# node_modules不存在
因为node_modules太大了，大概有150M左右，这里就不上传了，如果需要，你可以react-native init 项目名，
会自动生成node_modules，直接拷贝过来就可以用

# 移植RN到library中
为了更好模块化开发，我这里将所有RN的东西都移植到了rn目录下，包括node_modules、package.json、index.android.js等等，完全从项目根目录ReactNativeProject剥离出来，这样看起来更加的舒服，以后所有的RN模块代码都卸载rn的library中，详情请看代码

在移植的过程中，遇到了一个蛋疼的问题，这个问题是：

	java.lang.RuntimeException: ReferenceError: Can't find variable: __fbBatchedBridge

原因就在于

	mReactInstanceManager = ReactInstanceManager.builder()
	                .setApplication(getApplication())
	                .setBundleAssetName("index.android.bundle")
	                .setJSMainModuleName("index.android")
	                .addPackage(new MainReactPackage())
	                .setUseDeveloperSupport(BuildConfig.DEBUG)
	                .setInitialLifecycleState(LifecycleState.RESUMED)
	                .build();

代码里面有个setUseDeveloperSupport(BuildConfig.DEBUG)，这个BuildConfig.DEBUG是个大坑，因为它在library和application的值是不一样的，在library中不管你是assembleDebug还是assembleRelease，它都是返回的false，这个问题应该说是Gradle的限制bug，不过也提供了解决方案，解决方案就是在rn的build.gradle中加入

	android {
		publishNonDefault true
	}
	
在app中引入rn的library时，改成以下方式：


	//    compile project(":rn")
	debugCompile project(path: ':rn', configuration: 'debug')
	releaseCompile project(path: ':rn', configuration: 'release')
	
改完之后，我们就可以愉快的写代码了。

# 几个注意的点

官方建议如果在多个Activity或者Fragment使用RN，需要将ReactInstanceManager设置成单例。改成单例后获取ReactInstanceManager的代码如下：

    mReactInstanceManager = MyRNInstanceManager.getInstance();
    
单例代码如下：

	/**
	 * Created by HanHailong on 16/4/27.
	 */
	public class MyRNInstanceManager {
	
	    private MyRNInstanceManager() {
	    }
	
	    /**
	     * 返回{@link MyRNInstanceManager}单例
	     *
	     * @return
	     */
	    public static ReactInstanceManager getInstance() {
	        return Holder.mInstance;
	    }
	
	    private static class Holder {
	        private static final ReactInstanceManager mInstance = ReactInstanceManager.builder()
	                .setApplication(RNApplication.getInstance())
	                .setBundleAssetName("index.android.bundle")
	                .setJSMainModuleName("index.android")
	                .addPackage(new MainReactPackage())
	                .setUseDeveloperSupport(BuildConfig.DEBUG)
	                .setInitialLifecycleState(LifecycleState.RESUMED)
	                .build();
	    }
	}
	
# 添加自定义Native Module
添加自定义的Native Module，因为系统提供了ToastAndroid实现，为了防止FB提供的ToastAndroid冲突，这里改名ToastAndroid2

---
---

# 2016-5-4 添加了CustomImageViewManager
在定义的Native UI控件中，声明了两个方法，一个是imageToast方法，测试用的，一个是source方法，用来设置网络图片uri地址的，js中使用代码如下：

先导入：

	var CusImageView = require('./CustomImageView');

使用：

	customView() {
	    return(
	        <View style={styles.container}>
	          <CusImageView style={{width:200,height:200,backgroundColor:'red'}}
	            imageToast="呵呵呵"
	            source={{uri:'http://f.hiphotos.bdimg.com/imgad/pic/item/77c6a7efce1b9d1608043b8cf6deb48f8d546418.jpg'}}
	          />
	        </View>
	      );
	  }	

Java代码请看具体代码

效果图如下：
![](https://github.com/hanhailong/AndroidStudyResources/blob/master/screenshot/rn/rn_native_customimageview.png?raw=true)

# Gradle依赖改成本地源码编译依赖

在rn中注释掉，改成依赖ReactAndroid


	compile project(':ReactAndroid')
	//    compile('com.facebook.react:react-native:0.20.1') {
	//        exclude group: 'com.nineoldandroids', module: 'library'
	//        exclude(module: 'jackson-core')
	//        exclude(module: 'jsr305')
	//        exclude(module: 'commons-cli')
	//    }
	
	
项目根目录的*build.gradle*中添加*gradle-download-task*

	buildscript {
	    repositories {
	        jcenter()
	    }
	    dependencies {
	        classpath 'com.android.tools.build:gradle:2.1.0'
	        classpath 'de.undercouch:gradle-download-task:3.0.0'
	        // NOTE: Do not place your application dependencies here; they belong
	        // in the individual module build.gradle files
	    }
	}

别忘了改*settings.gradle*，因为我们直接用的node_modules中的，所以改成：

	include ':ReactAndroid'
	project(':ReactAndroid').projectDir = new File(
	        rootProject.projectDir, 'rn/node_modules/react-native/ReactAndroid')
	        
具体看代码吧，已上传GitHub

# TODO 热更新

