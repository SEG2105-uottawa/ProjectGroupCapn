
à0
[
ComplaintListTestcom.example.tutronappinitializationError2±µÞ¤ÀÎòî:±µÞ¤€ Ô’­)
ÿjava.lang.RuntimeException: Failed to instantiate test runner class androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner


at androidx.test.ext.junit.runners.AndroidJUnit4.throwInitializationError(AndroidJUnit4.java:129)
at androidx.test.ext.junit.runners.AndroidJUnit4.loadRunner(AndroidJUnit4.java:121)
at androidx.test.ext.junit.runners.AndroidJUnit4.loadRunner(AndroidJUnit4.java:82)
at androidx.test.ext.junit.runners.AndroidJUnit4.<init>(AndroidJUnit4.java:56)
at java.lang.reflect.Constructor.newInstance0(Native Method)
at java.lang.reflect.Constructor.newInstance(Constructor.java:343)
at org.junit.internal.builders.AnnotatedBuilder.buildRunner(AnnotatedBuilder.java:104)
at org.junit.internal.builders.AnnotatedBuilder.runnerForClass(AnnotatedBuilder.java:86)
at android.support.test.internal.runner.junit4.AndroidAnnotatedBuilder.runnerForClass(AndroidAnnotatedBuilder.java:63)
at org.junit.runners.model.RunnerBuilder.safeRunnerForClass(RunnerBuilder.java:70)
at org.junit.internal.builders.AllDefaultPossibilitiesBuilder.runnerForClass(AllDefaultPossibilitiesBuilder.java:37)
at android.support.test.internal.runner.AndroidRunnerBuilder.runnerForClass(AndroidRunnerBuilder.java:153)
at org.junit.runners.model.RunnerBuilder.safeRunnerForClass(RunnerBuilder.java:70)
at android.support.test.internal.runner.TestLoader.doCreateRunner(TestLoader.java:73)
at android.support.test.internal.runner.TestLoader.getRunnersFor(TestLoader.java:104)
at android.support.test.internal.runner.TestRequestBuilder.build(TestRequestBuilder.java:789)
at android.support.test.runner.AndroidJUnitRunner.buildRequest(AndroidJUnitRunner.java:539)
at android.support.test.runner.AndroidJUnitRunner.onStart(AndroidJUnitRunner.java:382)
at android.app.Instrumentation$InstrumentationThread.run(Instrumentation.java:2361)
Caused by: java.lang.reflect.InvocationTargetException
at java.lang.reflect.Constructor.newInstance0(Native Method)
at java.lang.reflect.Constructor.newInstance(Constructor.java:343)
at androidx.test.ext.junit.runners.AndroidJUnit4.loadRunner(AndroidJUnit4.java:112)
... 17 more
Caused by: java.lang.IllegalStateException: No instrumentation registered! Must run under a registering instrumentation.
at androidx.test.platform.app.InstrumentationRegistry.getInstrumentation(InstrumentationRegistry.java:45)
at androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner.createRunnerParams(AndroidJUnit4ClassRunner.java:53)
at androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner.<init>(AndroidJUnit4ClassRunner.java:48)
... 20 more¦java.lang.reflect.InvocationTargetException
at java.lang.reflect.Constructor.newInstance0(Native Method)
at java.lang.reflect.Constructor.newInstance(Constructor.javaÿjava.lang.RuntimeException: Failed to instantiate test runner class androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner


at androidx.test.ext.junit.runners.AndroidJUnit4.throwInitializationError(AndroidJUnit4.java:129)
at androidx.test.ext.junit.runners.AndroidJUnit4.loadRunner(AndroidJUnit4.java:121)
at androidx.test.ext.junit.runners.AndroidJUnit4.loadRunner(AndroidJUnit4.java:82)
at androidx.test.ext.junit.runners.AndroidJUnit4.<init>(AndroidJUnit4.java:56)
at java.lang.reflect.Constructor.newInstance0(Native Method)
at java.lang.reflect.Constructor.newInstance(Constructor.java:343)
at org.junit.internal.builders.AnnotatedBuilder.buildRunner(AnnotatedBuilder.java:104)
at org.junit.internal.builders.AnnotatedBuilder.runnerForClass(AnnotatedBuilder.java:86)
at android.support.test.internal.runner.junit4.AndroidAnnotatedBuilder.runnerForClass(AndroidAnnotatedBuilder.java:63)
at org.junit.runners.model.RunnerBuilder.safeRunnerForClass(RunnerBuilder.java:70)
at org.junit.internal.builders.AllDefaultPossibilitiesBuilder.runnerForClass(AllDefaultPossibilitiesBuilder.java:37)
at android.support.test.internal.runner.AndroidRunnerBuilder.runnerForClass(AndroidRunnerBuilder.java:153)
at org.junit.runners.model.RunnerBuilder.safeRunnerForClass(RunnerBuilder.java:70)
at android.support.test.internal.runner.TestLoader.doCreateRunner(TestLoader.java:73)
at android.support.test.internal.runner.TestLoader.getRunnersFor(TestLoader.java:104)
at android.support.test.internal.runner.TestRequestBuilder.build(TestRequestBuilder.java:789)
at android.support.test.runner.AndroidJUnitRunner.buildRequest(AndroidJUnitRunner.java:539)
at android.support.test.runner.AndroidJUnitRunner.onStart(AndroidJUnitRunner.java:382)
at android.app.Instrumentation$InstrumentationThread.run(Instrumentation.java:2361)
Caused by: java.lang.reflect.InvocationTargetException
at java.lang.reflect.Constructor.newInstance0(Native Method)
at java.lang.reflect.Constructor.newInstance(Constructor.java:343)
at androidx.test.ext.junit.runners.AndroidJUnit4.loadRunner(AndroidJUnit4.java:112)
... 17 more
Caused by: java.lang.IllegalStateException: No instrumentation registered! Must run under a registering instrumentation.
at androidx.test.platform.app.InstrumentationRegistry.getInstrumentation(InstrumentationRegistry.java:45)
at androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner.createRunnerParams(AndroidJUnit4ClassRunner.java:53)
at androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner.<init>(AndroidJUnit4ClassRunner.java:48)
... 20 more"÷

logcatandroidá
ÞC:\Users\16132\OneDrive - University of Ottawa\Desktop\P2\ProjectGroupCapn\app\build\outputs\androidTest-results\connected\Pixel_3a_XL_API_33(AVD) - 13\logcat-com.example.tutronapp.ComplaintListTest-initializationError.txt"Ä

device-infoandroid©
¦C:\Users\16132\OneDrive - University of Ottawa\Desktop\P2\ProjectGroupCapn\app\build\outputs\androidTest-results\connected\Pixel_3a_XL_API_33(AVD) - 13\device-info.pb"Å

device-info.meminfoandroid¢
ŸC:\Users\16132\OneDrive - University of Ottawa\Desktop\P2\ProjectGroupCapn\app\build\outputs\androidTest-results\connected\Pixel_3a_XL_API_33(AVD) - 13\meminfo"Å

device-info.cpuinfoandroid¢
ŸC:\Users\16132\OneDrive - University of Ottawa\Desktop\P2\ProjectGroupCapn\app\build\outputs\androidTest-results\connected\Pixel_3a_XL_API_33(AVD) - 13\cpuinfo*©
c
test-results.logOcom.google.testing.platform.runtime.android.driver.AndroidInstrumentationDriver³
°C:\Users\16132\OneDrive - University of Ottawa\Desktop\P2\ProjectGroupCapn\app\build\outputs\androidTest-results\connected\Pixel_3a_XL_API_33(AVD) - 13\testlog\test-results.log 2
text/plain