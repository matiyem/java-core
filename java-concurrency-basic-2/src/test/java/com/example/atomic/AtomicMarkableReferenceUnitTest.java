package com.example.atomic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicMarkableReference;

public class AtomicMarkableReferenceUnitTest {
//    AtomicMarkableReference یک کلاس عمومی است که هم ارجاع به یک شی و هم یک پرچم بولی را محصور می کند. این دو فیلد با هم جفت شده‌اند و می‌توانند به صورت اتمی، چه با هم یا به صورت جداگانه، به روز شوند.
//
//AtomicMarkableReference همچنین می تواند یک راه حل احتمالی در برابر مشکل ABA باشد.

    class Employee {
        private int id;
        private String name;

        Employee(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Test
    void givenMarkValueAsTrue_whenUsingIsMarkedMethod_thenMarkValueShouldBeTrue() {
        Employee employee = new Employee(123, "Mike");
//        توجه داشته باشید که AtomicMarkableReference یک جفت کلاس تودرتو ایستا دارد که refrence و flag را نگه می دارد.
//
//همچنین، می بینیم که هر دو متغیر final هستند. در نتیجه هر زمان که بخواهیم این متغیرها را تغییر دهیم، نمونه جدیدی از کلاس Pair ایجاد می شود و نمونه قدیمی جایگزین می شود.
        AtomicMarkableReference<Employee> employeeNode = new AtomicMarkableReference<Employee>(employee, true);

        Assertions.assertTrue(employeeNode.isMarked());
    }

    @Test
    void givenMarkValueAsFalse_whenUsingIsMarkedMethod_thenMarkValueShouldBeFalse() {
        Employee employee = new Employee(123, "Mike");
        AtomicMarkableReference<Employee> employeeNode = new AtomicMarkableReference<Employee>(employee, false);

        Assertions.assertFalse(employeeNode.isMarked());
    }

    @Test
    void whenUsingGetReferenceMethod_thenCurrentReferenceShouldBeReturned() {
        Employee employee = new Employee(123, "Mike");
        AtomicMarkableReference<Employee> employeeNode = new AtomicMarkableReference<Employee>(employee, true);
//        ما از متد getReference برای برگرداندن مقدار فعلی متغیر refrence استفاده می کنیم:
        Assertions.assertEquals(employee, employeeNode.getReference());
    }

    @Test
    void whenUsingGetMethod_thenCurrentReferenceAndMarkShouldBeReturned() {
        Employee employee = new Employee(123, "Mike");
        AtomicMarkableReference<Employee> employeeNode = new AtomicMarkableReference<Employee>(employee, true);

//        در مرحله بعد، زمانی که می خواهیم هم refrence فعلی و هم mark فعلی را بازیابی کنیم، از متد get استفاده می کنیم. برای به دست آوردن mark، باید یک آرایه boolean با اندازه حداقل یک به عنوان پارامتر ارسال کنیم، که در شاخص 0 مقدار فعلی متغیر بولی را ذخیره می کند. در همان زمان، متد مقدار فعلی مرجع را برمی گرداند:
//        این روش برای گرفتن هر دو فیلد refrence و mark کمی عجیب است زیرا کلاس جفت داخلی در معرض تماس گیرنده قرار نمی گیرد.
//        جاوا کلاس Pair<T, U> عمومی در API عمومی خود ندارد. دلیل اصلی این امر این است که ممکن است به جای ایجاد انواع متمایز، وسوسه شویم که از آن بیش از حد استفاده کنیم.
        boolean[] markHolder = new boolean[1];
        Employee currentEmployee = employeeNode.get(markHolder);

        Assertions.assertEquals(employee, currentEmployee);
        Assertions.assertTrue(markHolder[0]);
    }

    @Test
    void givenNewReferenceAndMark_whenUsingSetMethod_thenCurrentReferenceAndMarkShouldBeUpdated() {
        Employee employee = new Employee(123, "Mike");
        AtomicMarkableReference<Employee> employeeNode = new AtomicMarkableReference<Employee>(employee, true);

        Employee newEmployee = new Employee(124, "John");
//        در صورتی که بخواهیم هر دو فیلد مرجع و علامت را بدون قید و شرط به روز کنیم، باید از روش set استفاده کنیم. اگر حداقل یکی از مقادیر ارسال شده به عنوان پارامتر متفاوت باشد، مرجع و علامت به روز می شوند:
        employeeNode.set(newEmployee, false);

        Assertions.assertEquals(newEmployee, employeeNode.getReference());
//        برای بدست آوردن مقدار متغیر mark، باید متد isMarked را فراخوانی کنیم:
        Assertions.assertFalse(employeeNode.isMarked());
    }

    @Test
    void givenTheSameObjectReference_whenUsingAttemptMarkMethod_thenMarkShouldBeUpdated() {
        Employee employee = new Employee(123, "Mike");
        AtomicMarkableReference<Employee> employeeNode = new AtomicMarkableReference<Employee>(employee, true);

        Assertions.assertTrue(employeeNode.attemptMark(employee, false));
        Assertions.assertFalse(employeeNode.isMarked());
    }

    @Test
    void givenDifferentObjectReference_whenUsingAttemptMarkMethod_thenMarkShouldNotBeUpdated() {
        Employee employee = new Employee(123, "Mike");
        AtomicMarkableReference<Employee> employeeNode = new AtomicMarkableReference<Employee>(employee, true);
        Employee expectedEmployee = new Employee(123, "Mike");
//        در نهایت متد attemptMark را داریم. بررسی می کند که آیا مرجع فعلی برابر است با یک مرجع مورد انتظار ارسال شده به عنوان یک پارامتر. اگر مطابقت داشته باشند، مقدار علامت را به صورت اتمی روی مقدار به روز شده داده شده تنظیم می کند:
//        توجه به این نکته مهم است که این روش ممکن است به طور جعلی شکست بخورد حتی اگر مرجع مورد انتظار و فعلی برابر باشند. در نتیجه باید به بولی که توسط اجرای متد برگردانده شده است توجه کنیم.
//
//اگر علامت با موفقیت به روز شود، نتیجه درست است، یا در غیر این صورت نادرست است. با این حال، فراخوانی مکرر زمانی که مرجع فعلی برابر با مرجع مورد انتظار است، مقدار علامت را تغییر می دهد. در نتیجه، استفاده از این روش در ساختار حلقه while توصیه می شود.
//
//این شکست ممکن است در نتیجه الگوریتم اصلی مقایسه و مبادله (CAS) رخ دهد که توسط روش attemptMark برای به‌روزرسانی فیلدها استفاده می‌شود. اگر چندین رشته داشته باشیم که سعی می کنند یک مقدار را با استفاده از CAS به روز کنند، یکی از آنها موفق به تغییر مقدار می شود و به بقیه اطلاع داده می شود که به روز رسانی انجام نشد.
        Assertions.assertFalse(employeeNode.attemptMark(expectedEmployee, false));
        Assertions.assertTrue(employeeNode.isMarked());
    }

    @Test
    void givenCurrentReferenceAndCurrentMark_whenUsingCompareAndSet_thenReferenceAndMarkShouldBeUpdated() {
        Employee employee = new Employee(123, "Mike");
        AtomicMarkableReference<Employee> employeeNode = new AtomicMarkableReference<Employee>(employee, true);
        Employee newEmployee = new Employee(124, "John");
//        در مرحله بعد، اگر مرجع فعلی برابر با مرجع مورد انتظار باشد، روش compareAndSet هم مرجع و هم علامت را به مقادیر به‌روزرسانی شده به‌روزرسانی می‌کند، و علامت فعلی برابر با علامت مورد انتظار است.
//        همچنین، هنگام فراخوانی متد compareAndSet، اگر فیلدها به روز شده باشند، true یا در صورت عدم موفقیت آپدیت، false دریافت می کنیم.
        Assertions.assertTrue(employeeNode.compareAndSet(employee, newEmployee, true, false));
        Assertions.assertEquals(newEmployee, employeeNode.getReference());
        Assertions.assertFalse(employeeNode.isMarked());
    }

    @Test
    void givenNotCurrentReferenceAndCurrentMark_whenUsingCompareAndSet_thenReferenceAndMarkShouldNotBeUpdated() {
        Employee employee = new Employee(123, "Mike");
        AtomicMarkableReference<Employee> employeeNode = new AtomicMarkableReference<Employee>(employee, true);
        Employee newEmployee = new Employee(124, "John");

        Assertions.assertFalse(employeeNode.compareAndSet(new Employee(1234, "Steve"), newEmployee, true, false));
        Assertions.assertEquals(employee, employeeNode.getReference());
        Assertions.assertTrue(employeeNode.isMarked());
    }

    @Test
    void givenCurrentReferenceAndNotCurrentMark_whenUsingCompareAndSet_thenReferenceAndMarkShouldNotBeUpdated() {
        Employee employee = new Employee(123, "Mike");
        AtomicMarkableReference<Employee> employeeNode = new AtomicMarkableReference<Employee>(employee, true);
        Employee newEmployee = new Employee(124, "John");

        Assertions.assertFalse(employeeNode.compareAndSet(employee, newEmployee, false, true));
        Assertions.assertEquals(employee, employeeNode.getReference());
        Assertions.assertTrue(employeeNode.isMarked());
    }

    @Test
    void givenNotCurrentReferenceAndNotCurrentMark_whenUsingCompareAndSet_thenReferenceAndMarkShouldNotBeUpdated() {
        Employee employee = new Employee(123, "Mike");
        AtomicMarkableReference<Employee> employeeNode = new AtomicMarkableReference<Employee>(employee, true);
        Employee newEmployee = new Employee(124, "John");

        Assertions.assertFalse(employeeNode.compareAndSet(new Employee(1234, "Steve"), newEmployee, false, true));
        Assertions.assertEquals(employee, employeeNode.getReference());
        Assertions.assertTrue(employeeNode.isMarked());
    }

    @Test
    void givenCurrentReferenceAndCurrentMark_whenUsingWeakCompareAndSet_thenReferenceAndMarkShouldBeUpdated() {
        Employee employee = new Employee(123, "Mike");
        AtomicMarkableReference<Employee> employeeNode = new AtomicMarkableReference<Employee>(employee, true);
        Employee newEmployee = new Employee(124, "John");
//متد weakCompareAndSet  باید نسخه ضعیف تری از روش compareAndSet باشد. به این معنی که مانند compareAndSet ضمانت‌های سفارش حافظه قوی را ارائه نمی‌کند. همچنین، ممکن است به طور جعلی در دسترسی انحصاری در سطح سخت افزار شکست بخورد.
//
//این مشخصات روش weakCompareAndSet  است. با این حال، در حال حاضر، weakCompareAndSet  به سادگی متد compareAndSet را تحت پوشش فراخوانی می کند. بنابراین، آنها همان پیاده سازی قوی را دارند.
//
//اگرچه این دو روش در حال حاضر اجرای یکسانی دارند، اما باید بر اساس مشخصات آنها از آنها استفاده کنیم. بنابراین، ما باید weakCompareAndSet  را به عنوان یک اتمی ضعیف در نظر بگیریم.
//
//اتم های ضعیف در برخی از پلتفرم ها و در برخی شرایط می توانند ارزان تر باشند. به عنوان مثال، اگر می‌خواهیم یک CompareAndSet را در یک حلقه انجام دهیم، شاید بهتر باشد از نسخه ضعیف‌تر استفاده کنیم. در این حالت، ما در نهایت وضعیت را به‌روزرسانی می‌کنیم، زیرا در یک حلقه هستیم، بنابراین خرابی‌های جعلی بر صحت برنامه تأثیری نخواهد داشت.
//
//نکته پایانی این است که اتم های ضعیف می توانند در برخی موارد خاص مفید باشند و در نتیجه برای هر سناریو ممکنی قابل اجرا نیستند. بنابراین، وقتی شک دارید، compareAndSet قوی‌تر را ترجیح دهید.
        Assertions.assertTrue(employeeNode.weakCompareAndSet(employee, newEmployee, true, false));
        Assertions.assertEquals(newEmployee, employeeNode.getReference());
        Assertions.assertFalse(employeeNode.isMarked());
    }

    @Test
    void givenNotCurrentReferenceAndCurrentMark_whenUsingWeakCompareAndSet_thenReferenceAndMarkShouldNotBeUpdated() {
        Employee employee = new Employee(123, "Mike");
        AtomicMarkableReference<Employee> employeeNode = new AtomicMarkableReference<Employee>(employee, true);
        Employee newEmployee = new Employee(124, "John");

        Assertions.assertFalse(employeeNode.weakCompareAndSet(new Employee(1234, "Steve"), newEmployee, true, false));
        Assertions.assertEquals(employee, employeeNode.getReference());
        Assertions.assertTrue(employeeNode.isMarked());
    }

    @Test
    void givenCurrentReferenceAndNotCurrentMark_whenUsingWeakCompareAndSet_thenReferenceAndMarkShouldNotBeUpdated() {
        Employee employee = new Employee(123, "Mike");
        AtomicMarkableReference<Employee> employeeNode = new AtomicMarkableReference<Employee>(employee, true);
        Employee newEmployee = new Employee(124, "John");

        Assertions.assertFalse(employeeNode.weakCompareAndSet(employee, newEmployee, false, true));
        Assertions.assertEquals(employee, employeeNode.getReference());
        Assertions.assertTrue(employeeNode.isMarked());
    }

    @Test
    void givenNotCurrentReferenceAndNotCurrentMark_whenUsingWeakCompareAndSet_thenReferenceAndMarkShouldNotBeUpdated() {
        Employee employee = new Employee(123, "Mike");
        AtomicMarkableReference<Employee> employeeNode = new AtomicMarkableReference<Employee>(employee, true);
        Employee newEmployee = new Employee(124, "John");

        Assertions.assertFalse(employeeNode.weakCompareAndSet(new Employee(1234, "Steve"), newEmployee, false, true));
        Assertions.assertEquals(employee, employeeNode.getReference());
        Assertions.assertTrue(employeeNode.isMarked());
    }
}
