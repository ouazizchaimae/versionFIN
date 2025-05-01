import React from 'react';
import {KeyboardAvoidingView, Platform, SafeAreaView} from 'react-native';
import {globalStyle} from '../../../../../../shared/globalStyle';

import UniteAdminTabNavigation from '../../../../../../navigation/admin/referentiel/UniteAdminTabNavigation';

const UniteAdmin = () => {
  return (
    <SafeAreaView style={globalStyle.myContainer}>

      <KeyboardAvoidingView
        behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
        style={{ flex: 1 }} >
        <UniteAdminTabNavigation />
      </KeyboardAvoidingView>
    </SafeAreaView>
  );
};

export default UniteAdmin;
