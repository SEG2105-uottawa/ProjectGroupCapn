
�C
n
ComplaintListTestcom.example.tutronapp&testComplaintListRecyclerViewItemCount2�������:��������<
�android.view.InflateException: Binary XML file line #32 in com.example.tutronapp:layout/activity_manage_complaints: Failed to resolve attribute at index 1: TypedValue{t=0x2/d=0x7f040005 a=-1}
Caused by: java.lang.UnsupportedOperationException: Failed to resolve attribute at index 1: TypedValue{t=0x2/d=0x7f040005 a=-1}
at android.content.res.TypedArray.getLayoutDimension(TypedArray.java:826)
at android.view.ViewGroup$LayoutParams.setBaseAttributes(ViewGroup.java:8232)
at android.view.ViewGroup$MarginLayoutParams.<init>(ViewGroup.java:8429)
at androidx.constraintlayout.widget.ConstraintLayout$LayoutParams.<init>(ConstraintLayout.java:2910)
at androidx.constraintlayout.widget.ConstraintLayout.generateLayoutParams(ConstraintLayout.java:1934)
at androidx.constraintlayout.widget.ConstraintLayout.generateLayoutParams(ConstraintLayout.java:486)
at android.view.LayoutInflater.rInflate(LayoutInflater.java:1123)
at android.view.LayoutInflater.rInflateChildren(LayoutInflater.java:1082)
at android.view.LayoutInflater.rInflate(LayoutInflater.java:1124)
at android.view.LayoutInflater.rInflateChildren(LayoutInflater.java:1082)
at android.view.LayoutInflater.inflate(LayoutInflater.java:680)
at android.view.LayoutInflater.inflate(LayoutInflater.java:532)
at android.view.LayoutInflater.inflate(LayoutInflater.java:479)
at com.example.tutronapp.ComplaintListTest.testComplaintListRecyclerViewItemCount(ComplaintListTest.java:63)
at java.lang.reflect.Method.invoke(Native Method)
at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59)
at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:56)
at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
at androidx.test.internal.runner.junit4.statement.RunBefores.evaluate(RunBefores.java:80)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at androidx.test.ext.junit.runners.AndroidJUnit4.run(AndroidJUnit4.java:162)
at org.junit.runners.Suite.runChild(Suite.java:128)
at org.junit.runners.Suite.runChild(Suite.java:27)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:67)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:58)
at androidx.test.runner.AndroidJUnitRunner.onStart(AndroidJUnitRunner.java:446)
at android.app.Instrumentation$InstrumentationThread.run(Instrumentation.java:2205)'java.lang.UnsupportedOperationException�android.view.InflateException: Binary XML file line #32 in com.example.tutronapp:layout/activity_manage_complaints: Failed to resolve attribute at index 1: TypedValue{t=0x2/d=0x7f040005 a=-1}
Caused by: java.lang.UnsupportedOperationException: Failed to resolve attribute at index 1: TypedValue{t=0x2/d=0x7f040005 a=-1}
at android.content.res.TypedArray.getLayoutDimension(TypedArray.java:826)
at android.view.ViewGroup$LayoutParams.setBaseAttributes(ViewGroup.java:8232)
at android.view.ViewGroup$MarginLayoutParams.<init>(ViewGroup.java:8429)
at androidx.constraintlayout.widget.ConstraintLayout$LayoutParams.<init>(ConstraintLayout.java:2910)
at androidx.constraintlayout.widget.ConstraintLayout.generateLayoutParams(ConstraintLayout.java:1934)
at androidx.constraintlayout.widget.ConstraintLayout.generateLayoutParams(ConstraintLayout.java:486)
at android.view.LayoutInflater.rInflate(LayoutInflater.java:1123)
at android.view.LayoutInflater.rInflateChildren(LayoutInflater.java:1082)
at android.view.LayoutInflater.rInflate(LayoutInflater.java:1124)
at android.view.LayoutInflater.rInflateChildren(LayoutInflater.java:1082)
at android.view.LayoutInflater.inflate(LayoutInflater.java:680)
at android.view.LayoutInflater.inflate(LayoutInflater.java:532)
at android.view.LayoutInflater.inflate(LayoutInflater.java:479)
at com.example.tutronapp.ComplaintListTest.testComplaintListRecyclerViewItemCount(ComplaintListTest.java:63)
at java.lang.reflect.Method.invoke(Native Method)
at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59)
at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:56)
at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
at androidx.test.internal.runner.junit4.statement.RunBefores.evaluate(RunBefores.java:80)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at androidx.test.ext.junit.runners.AndroidJUnit4.run(AndroidJUnit4.java:162)
at org.junit.runners.Suite.runChild(Suite.java:128)
at org.junit.runners.Suite.runChild(Suite.java:27)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:67)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:58)
at androidx.test.runner.AndroidJUnitRunner.onStart(AndroidJUnitRunner.java:446)
at android.app.Instrumentation$InstrumentationThread.run(Instrumentation.java:2205)"�

logcatandroid�
�C:\Users\16132\OneDrive - University of Ottawa\Desktop\P2\ProjectGroupCapn\app\build\outputs\androidTest-results\connected\Pixel_4a_API_30(AVD) - 11\logcat-com.example.tutronapp.ComplaintListTest-testComplaintListRecyclerViewItemCount.txt"�

device-infoandroid�
�C:\Users\16132\OneDrive - University of Ottawa\Desktop\P2\ProjectGroupCapn\app\build\outputs\androidTest-results\connected\Pixel_4a_API_30(AVD) - 11\device-info.pb"�

device-info.meminfoandroid�
�C:\Users\16132\OneDrive - University of Ottawa\Desktop\P2\ProjectGroupCapn\app\build\outputs\androidTest-results\connected\Pixel_4a_API_30(AVD) - 11\meminfo"�

device-info.cpuinfoandroid�
�C:\Users\16132\OneDrive - University of Ottawa\Desktop\P2\ProjectGroupCapn\app\build\outputs\androidTest-results\connected\Pixel_4a_API_30(AVD) - 11\cpuinfo�
n
ComplaintListTestcom.example.tutronapp&testComplaintListViewHolderDataBinding2�������:������"�

logcatandroid�
�C:\Users\16132\OneDrive - University of Ottawa\Desktop\P2\ProjectGroupCapn\app\build\outputs\androidTest-results\connected\Pixel_4a_API_30(AVD) - 11\logcat-com.example.tutronapp.ComplaintListTest-testComplaintListViewHolderDataBinding.txt"�

device-infoandroid�
�C:\Users\16132\OneDrive - University of Ottawa\Desktop\P2\ProjectGroupCapn\app\build\outputs\androidTest-results\connected\Pixel_4a_API_30(AVD) - 11\device-info.pb"�

device-info.meminfoandroid�
�C:\Users\16132\OneDrive - University of Ottawa\Desktop\P2\ProjectGroupCapn\app\build\outputs\androidTest-results\connected\Pixel_4a_API_30(AVD) - 11\meminfo"�

device-info.cpuinfoandroid�
�C:\Users\16132\OneDrive - University of Ottawa\Desktop\P2\ProjectGroupCapn\app\build\outputs\androidTest-results\connected\Pixel_4a_API_30(AVD) - 11\cpuinfo�C
e
ComplaintListTestcom.example.tutronapptestComplaintListRecyclerView2�������:��������<
�android.view.InflateException: Binary XML file line #32 in com.example.tutronapp:layout/activity_manage_complaints: Failed to resolve attribute at index 1: TypedValue{t=0x2/d=0x7f040005 a=-1}
Caused by: java.lang.UnsupportedOperationException: Failed to resolve attribute at index 1: TypedValue{t=0x2/d=0x7f040005 a=-1}
at android.content.res.TypedArray.getLayoutDimension(TypedArray.java:826)
at android.view.ViewGroup$LayoutParams.setBaseAttributes(ViewGroup.java:8232)
at android.view.ViewGroup$MarginLayoutParams.<init>(ViewGroup.java:8429)
at androidx.constraintlayout.widget.ConstraintLayout$LayoutParams.<init>(ConstraintLayout.java:2910)
at androidx.constraintlayout.widget.ConstraintLayout.generateLayoutParams(ConstraintLayout.java:1934)
at androidx.constraintlayout.widget.ConstraintLayout.generateLayoutParams(ConstraintLayout.java:486)
at android.view.LayoutInflater.rInflate(LayoutInflater.java:1123)
at android.view.LayoutInflater.rInflateChildren(LayoutInflater.java:1082)
at android.view.LayoutInflater.rInflate(LayoutInflater.java:1124)
at android.view.LayoutInflater.rInflateChildren(LayoutInflater.java:1082)
at android.view.LayoutInflater.inflate(LayoutInflater.java:680)
at android.view.LayoutInflater.inflate(LayoutInflater.java:532)
at android.view.LayoutInflater.inflate(LayoutInflater.java:479)
at com.example.tutronapp.ComplaintListTest.testComplaintListRecyclerView(ComplaintListTest.java:43)
at java.lang.reflect.Method.invoke(Native Method)
at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59)
at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:56)
at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
at androidx.test.internal.runner.junit4.statement.RunBefores.evaluate(RunBefores.java:80)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at androidx.test.ext.junit.runners.AndroidJUnit4.run(AndroidJUnit4.java:162)
at org.junit.runners.Suite.runChild(Suite.java:128)
at org.junit.runners.Suite.runChild(Suite.java:27)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:67)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:58)
at androidx.test.runner.AndroidJUnitRunner.onStart(AndroidJUnitRunner.java:446)
at android.app.Instrumentation$InstrumentationThread.run(Instrumentation.java:2205)'java.lang.UnsupportedOperationException�android.view.InflateException: Binary XML file line #32 in com.example.tutronapp:layout/activity_manage_complaints: Failed to resolve attribute at index 1: TypedValue{t=0x2/d=0x7f040005 a=-1}
Caused by: java.lang.UnsupportedOperationException: Failed to resolve attribute at index 1: TypedValue{t=0x2/d=0x7f040005 a=-1}
at android.content.res.TypedArray.getLayoutDimension(TypedArray.java:826)
at android.view.ViewGroup$LayoutParams.setBaseAttributes(ViewGroup.java:8232)
at android.view.ViewGroup$MarginLayoutParams.<init>(ViewGroup.java:8429)
at androidx.constraintlayout.widget.ConstraintLayout$LayoutParams.<init>(ConstraintLayout.java:2910)
at androidx.constraintlayout.widget.ConstraintLayout.generateLayoutParams(ConstraintLayout.java:1934)
at androidx.constraintlayout.widget.ConstraintLayout.generateLayoutParams(ConstraintLayout.java:486)
at android.view.LayoutInflater.rInflate(LayoutInflater.java:1123)
at android.view.LayoutInflater.rInflateChildren(LayoutInflater.java:1082)
at android.view.LayoutInflater.rInflate(LayoutInflater.java:1124)
at android.view.LayoutInflater.rInflateChildren(LayoutInflater.java:1082)
at android.view.LayoutInflater.inflate(LayoutInflater.java:680)
at android.view.LayoutInflater.inflate(LayoutInflater.java:532)
at android.view.LayoutInflater.inflate(LayoutInflater.java:479)
at com.example.tutronapp.ComplaintListTest.testComplaintListRecyclerView(ComplaintListTest.java:43)
at java.lang.reflect.Method.invoke(Native Method)
at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59)
at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:56)
at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
at androidx.test.internal.runner.junit4.statement.RunBefores.evaluate(RunBefores.java:80)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at androidx.test.ext.junit.runners.AndroidJUnit4.run(AndroidJUnit4.java:162)
at org.junit.runners.Suite.runChild(Suite.java:128)
at org.junit.runners.Suite.runChild(Suite.java:27)
at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:67)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:58)
at androidx.test.runner.AndroidJUnitRunner.onStart(AndroidJUnitRunner.java:446)
at android.app.Instrumentation$InstrumentationThread.run(Instrumentation.java:2205)"�

logcatandroid�
�C:\Users\16132\OneDrive - University of Ottawa\Desktop\P2\ProjectGroupCapn\app\build\outputs\androidTest-results\connected\Pixel_4a_API_30(AVD) - 11\logcat-com.example.tutronapp.ComplaintListTest-testComplaintListRecyclerView.txt"�

device-infoandroid�
�C:\Users\16132\OneDrive - University of Ottawa\Desktop\P2\ProjectGroupCapn\app\build\outputs\androidTest-results\connected\Pixel_4a_API_30(AVD) - 11\device-info.pb"�

device-info.meminfoandroid�
�C:\Users\16132\OneDrive - University of Ottawa\Desktop\P2\ProjectGroupCapn\app\build\outputs\androidTest-results\connected\Pixel_4a_API_30(AVD) - 11\meminfo"�

device-info.cpuinfoandroid�
�C:\Users\16132\OneDrive - University of Ottawa\Desktop\P2\ProjectGroupCapn\app\build\outputs\androidTest-results\connected\Pixel_4a_API_30(AVD) - 11\cpuinfo" *�
c
test-results.logOcom.google.testing.platform.runtime.android.driver.AndroidInstrumentationDriver�
�C:\Users\16132\OneDrive - University of Ottawa\Desktop\P2\ProjectGroupCapn\app\build\outputs\androidTest-results\connected\Pixel_4a_API_30(AVD) - 11\testlog\test-results.log 2
text/plain