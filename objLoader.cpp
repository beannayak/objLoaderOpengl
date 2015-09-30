
#include "iostream"
#include "fstream"
#include "GL/glut.h"
#include "string"
#include "stdlib.h"
#include "math.h"
using namespace std;

struct point3D {
	float vx;
	float vy; 
	float vz;
};

struct face3D {
	int a;
	int b;
	int c;
	int d;
	int e;
	face3D (){
		e = 0;
	}
};

float _angle = 0.0, zoomFactor = -2.5, heightFactor = 0.0;
bool pause;
point3D *vertex;
face3D *facex;
int faceLength;

struct mysubstring {
	string *container;
	int length;
};


mysubstring substring(string text, string delimiter){
	int extent = 0;
	int dummy, index;
	mysubstring contained;

	dummy = text.length() - delimiter.length();
	for (int x=0; x <= dummy; x++){
		if (text.substr(x, delimiter.length()) == delimiter){
			extent += 1;
		}
	}

	contained.length = extent + 1;

	contained.container = new string[extent+1];
	index = 0;
	while (text.find_first_of(delimiter) != text.npos){
		dummy = text.find_first_of (delimiter);
		contained.container[index] = text.substr(0, dummy);
		text = text.substr(dummy + 1);
		index += 1;
	}
	contained.container[index] = text;
	return contained;
}

void checkLength(const char* filename, int &vertexCount, int &normalCount, int &faceCount){
	string a, line;
	fstream myfile;
	myfile.open (filename, ios::in);

	vertexCount = normalCount = faceCount = 0;
	while (!(myfile.eof())) {
		getline (myfile, line);
		
		if (line[0] != 13){
			a = line.substr(0, line.find_first_of (" "));
			if (a == "v") {
				vertexCount += 1;
			} else if (a == "vn") {
				normalCount += 1;
			} else if (a == "f") {
				faceCount += 1;
			}
		}
	}	
	faceLength = faceCount;
	myfile.close();
}

void objLoader (const char* filename) {
	string line, dummy;
	int vertices, vertexNormals, faces;

	mysubstring contains;

	checkLength(filename, vertices, vertexNormals, faces);

	vertex = new point3D [vertices];
	facex = new face3D [faces];

	int index, indexf;

	index = 1;
	indexf = 1;
	fstream myfile1;
	myfile1.open (filename, ios::in);
	while (!(myfile1.eof())) {
		getline (myfile1, line);

		dummy = line.substr(0, line.find_first_of (" "));
		if (dummy == "v") {
			contains = substring (line, " ");
			vertex[index].vx = atof(contains.container[1].c_str());
			vertex[index].vy = atof(contains.container[2].c_str());
			vertex[index].vz = atof(contains.container[3].c_str());
			index += 1;
			delete[] contains.container;
		} else if (dummy == "f") {
			contains = substring (line, " ");
			facex[indexf].b = atoi(contains.container[1].c_str());
			facex[indexf].c = atoi(contains.container[2].c_str());
			facex[indexf].d = atoi(contains.container[3].c_str());
			if (contains.length >= 5){
				facex[indexf].e = atoi(contains.container[4].c_str());
			}
			indexf += 1;
			delete[] contains.container;
		}
	}	
	myfile1.close();
}

void handleKeyPress(unsigned char key, int x, int y){
	switch (key){
		case 27:
			exit(0);
		case 87:
		case 119:
			glutFullScreen();
			break;
		case 83:
		case 115:
			glutPositionWindow(100,100);
			glutReshapeWindow(400, 400);
			break;
		case 73:
		case 105:
			zoomFactor += 0.1;
			glutPostRedisplay();
			break;
		case 79:
		case 111:
			zoomFactor -= 0.1;
			glutPostRedisplay();
			break;
		case 80:
		case 112:
			if (pause){
				pause = false;
			} else {
				pause = true;
			}
			break;
		case 90:
		case 122:
			heightFactor -= 0.05;
			glutPostRedisplay();
			break;
		case 88:
		case 120:
			heightFactor += 0.05;
			glutPostRedisplay();
			break;
	}
}

void initRendering(){
	glEnable(GL_DEPTH_TEST);		
	objLoader("doughnut.obj");
	pause = false;
}

void handleResize(int w, int h){
	glViewport(0, 0, w, h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	
	glFrustum (-0.2, 0.2, -0.2, 0.2, 0.1, 20.0);
}

void drawScene (){
	float a, b, c;
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glMatrixMode (GL_MODELVIEW);
	glLoadIdentity();
	
	glTranslatef(0.0f, heightFactor, zoomFactor);
	glRotatef (_angle, 1.0, 1.0, 1.0);
	
	glBegin (GL_LINE_LOOP);
		for (int i=1; i<=faceLength; i++){
			a = vertex[facex[i].b].vx;
			b = vertex[facex[i].b].vy;
			c = vertex[facex[i].b].vz;
			glVertex3f (a, b, c);

			a = vertex[facex[i].c].vx;
			b = vertex[facex[i].c].vy;
			c = vertex[facex[i].c].vz;
			glVertex3f (a, b, c);

			a = vertex[facex[i].d].vx;
			b = vertex[facex[i].d].vy;
			c = vertex[facex[i].d].vz;
			glVertex3f (a, b, c);

			if (facex[i].e != 0){
				a = vertex[facex[i].e].vx;
				b = vertex[facex[i].e].vy;
				c = vertex[facex[i].e].vz;
				glVertex3f (a, b, c);
			}
		}
	glEnd();
	
	glutSwapBuffers();
}

void update(int value) {
	if (!(pause)){
		_angle += 2.0f;
		if (_angle > 360) {
			_angle -= 360;
		}
		glutPostRedisplay(); //Tell GLUT that the display has changed
	}
	
	glutTimerFunc(25, update, 0);
}

int _tmain(int argc, char** argv){
	glutInit (&argc, argv);
	glutInitDisplayMode (GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
	
	glutInitWindowSize(400,400);
	glutCreateWindow ("A character: using Obj loader: By Beenayak");
	initRendering();

	glutDisplayFunc (drawScene);
	glutKeyboardFunc (handleKeyPress);
	glutReshapeFunc (handleResize);	

	glutTimerFunc(25, update, 0); //Add a timer
	glutMainLoop();

	return 0;
}
