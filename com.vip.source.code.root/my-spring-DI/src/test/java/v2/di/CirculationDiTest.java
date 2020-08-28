package v2.di;

import edu.dongnao.courseware.beans.BeanReference;
import edu.dongnao.courseware.beans.GenericBeanDefinition;
import edu.dongnao.courseware.beans.PreBuildBeanFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CirculationDiTest {

	static PreBuildBeanFactory bf = new PreBuildBeanFactory();

	@Test
	public void testCirculationDI() throws Exception {
		GenericBeanDefinition bd = new GenericBeanDefinition();
		bd.setBeanClass(Niulang.class);
		List<Object> args = new ArrayList<>();
		args.add("nulang");
		args.add(new BeanReference("zhinv"));
		bd.setConstructorArgumentValues(args);
		bf.registerBeanDefinition("nl", bd);

		bd = new GenericBeanDefinition();
		bd.setBeanClass(Zhinv.class);
		args = new ArrayList<>();
		args.add(new BeanReference("nl"));
		bd.setConstructorArgumentValues(args);
		bf.registerBeanDefinition("zhinv", bd);

		bf.preInstantiateSingletons();
	}
}
